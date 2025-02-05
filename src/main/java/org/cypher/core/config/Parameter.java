package org.cypher.core.config;

public interface Parameter {

  interface CommonConstant {
    byte ADD_PRE_FIX_BYTE_MAINNET = (byte) 0x1c;   //41 + address
    byte ADD_PRE_FIX_BYTE_TESTNET = (byte) 0xa0;   //a0 + address
    int ADDRESS_SIZE = 21;
  }

}
