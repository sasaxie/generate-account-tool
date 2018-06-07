# generate-account-tool

java-tron generate account tool

## Quick start

```shell
$ ./gradlew build
```

## CMD

```shell
cd generate-account-tool/
java -jar build/libs/GenerateAccountTool.jar --number 2 --netType testnet
```

**number:** The number of generated accounts

**netType:** Net type: testnet/mainnet

show:

```shell
$ Order: 1
$ Private Key: 1ae53d5199736a523f53ae61ac7c6a8f81a299211a5547e89e89955b1c3feece
$ Public Key: 0459d2b2e3daf4969df9ac4038a4801bd9a2beb396ecbaa10d6a3d0c3e375886f6a80bc202aee694bc24f10e144528d768972c2e16fb84b062dad9c482d0e97ff3
$ Address(Base58): TQX4LxaYzqwRf6YczefcfrTDqNczgkmUMK
$ Address(Hex): 419f97d8680e980506e2eb49dd315cd358cd82b381
$ 
```

## GUI

```shell
cd generate-account-tool/
java -jar build/libs/GenerateAccountToolGUI.jar
```

show:

<img src="/src/main/resources/show.png?raw=true">