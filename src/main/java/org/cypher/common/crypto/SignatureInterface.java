package org.cypher.common.crypto;

public interface SignatureInterface {
    boolean validateComponents();

    byte[] toByteArray();
}