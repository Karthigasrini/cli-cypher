package org.cypher.common.crypto;

import java.security.SignatureException;

public interface SignInterface {

  byte[] hash(byte[] message);

  byte[] getPrivateKey();

  byte[] getPubKey();

  byte[] getAddress();

  String signHash(byte[] hash);

  byte[] signToAddress(byte[] messageHash, String signatureBase64) throws SignatureException;

  byte[] getNodeId();

  byte[] Base64toBytes (String signature);

  byte[] getPrivKeyBytes();

  SignatureInterface sign(byte[] hash);
}
