package com.code402;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dk.brics.automaton.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App implements RequestHandler<Request, Response> {
  public Response match(String haystack, String needle) {
    ArrayList<String> hits = new ArrayList<String>();
    RegExp r;
    try {
      r = new RegExp(needle, RegExp.NONE);
    } catch (IllegalArgumentException iae) {
      return new Response(null, iae.getMessage());
    }

    RunAutomaton a = new RunAutomaton(r.toAutomaton());

    AutomatonMatcher m = a.newMatcher(haystack);
    while (m.find()) {
      hits.add(m.group());
    }

    int len = hits.size();
    if (len > 10) len = 10;
    String[] strings = Arrays.copyOf(hits.toArray(), len, String[].class);
    return new Response(strings, null);
  }

  public Response handleRequest(Request request, Context context) {
    System.out.println(
        "Received request: needle=" + request.getNeedle() + ", haystack=" + request.getHaystack());
    return match(request.haystack, request.needle);
  }

  public static void main(String[] args) {
    String haystack = args[0];
    String needle = args[1];
    Response rv = (new App()).match(haystack, needle);

    if (rv.error != null) {
      System.err.println(rv.error);
      System.exit(1);
    }

    for (int i = 0; i < rv.getHits().length; i++) {
      System.out.println(rv.getHits()[i]);
    }
  }
}
