package com.math.calculator.history;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class User {

    private Long id;

    private String username;

    private List<CalcResult> calcResults;

   public User(String username, CalcResult result) {
        id = generateUniqueId();
        this.username = username;
        calcResults = Collections.singletonList(result);
    }

    Long getId() {
        return id;
    }

    String getUsername() {
        return username;
    }

    List<CalcResult> getCalcResults() {
        return calcResults;
    }

    private Long generateUniqueId() {
        long val = -1;
        do {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        } while (val < 0);
        return val;
    }
}
