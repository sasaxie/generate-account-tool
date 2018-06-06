package org.tron.constant;

public interface Contant {
  interface Identity {
    byte ADDRESS_PREFIX_TESTNET = (byte) 0xa0;
    byte ADDRESS_PREFIX_MAINNET = (byte) 0x41;
  }

  interface NetType {
    String TESTNET = "testnet";
    String MAINNET = "mainnet";
  }
}
