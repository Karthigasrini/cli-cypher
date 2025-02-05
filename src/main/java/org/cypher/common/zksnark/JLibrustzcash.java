package org.cypher.common.zksnark;

import org.cypher.common.zksnark.LibrustzcashParam.BindingSigParams;
import org.cypher.common.zksnark.LibrustzcashParam.CheckOutputNewParams;
import org.cypher.common.zksnark.LibrustzcashParam.CheckOutputParams;
import org.cypher.common.zksnark.LibrustzcashParam.CheckSpendNewParams;
import org.cypher.common.zksnark.LibrustzcashParam.CheckSpendParams;
import org.cypher.common.zksnark.LibrustzcashParam.ComputeCmParams;
import org.cypher.common.zksnark.LibrustzcashParam.ComputeNfParams;
import org.cypher.common.zksnark.LibrustzcashParam.CrhIvkParams;
import org.cypher.common.zksnark.LibrustzcashParam.FinalCheckNewParams;
import org.cypher.common.zksnark.LibrustzcashParam.FinalCheckParams;
import org.cypher.common.zksnark.LibrustzcashParam.InitZksnarkParams;
import org.cypher.common.zksnark.LibrustzcashParam.IvkToPkdParams;
import org.cypher.common.zksnark.LibrustzcashParam.KaAgreeParams;
import org.cypher.common.zksnark.LibrustzcashParam.KaDerivepublicParams;
import org.cypher.common.zksnark.LibrustzcashParam.MerkleHashParams;
import org.cypher.common.zksnark.LibrustzcashParam.OutputProofParams;
import org.cypher.common.zksnark.LibrustzcashParam.SpendProofParams;
import org.cypher.common.zksnark.LibrustzcashParam.SpendSigParams;
import org.cypher.core.exception.ZksnarkException;

public class JLibrustzcash {
  private static Librustzcash INSTANCE = LibrustzcashWrapper.getInstance();

  public static void librustzcashInitZksnarkParams(InitZksnarkParams params) {
    INSTANCE.librustzcashInitZksnarkParams(params.getSpend_path(),
        params.getSpend_hash(), params.getOutput_path(), params.getOutput_hash());
  }

  public static void librustzcashCrhIvk(CrhIvkParams params) {
    INSTANCE.librustzcashCrhIvk(params.getAk(), params.getNk(), params.getIvk());
  }

  public static boolean librustzcashKaAgree(KaAgreeParams params) {
    return INSTANCE.librustzcashSaplingKaAgree(params.getP(), params.getSk(), params.getResult());
  }

  public static boolean librustzcashComputeCm(ComputeCmParams params) {
    return INSTANCE.librustzcashSaplingComputeCm(params.getD(), params.getPkD(),
        params.getValue(), params.getR(), params.getCm());
  }

  public static boolean librustzcashComputeNf(ComputeNfParams params) {
    INSTANCE.librustzcashSaplingComputeNf(params.getD(), params.getPkD(), params.getValue(),
        params.getR(), params.getAk(), params.getNk(), params.getPosition(), params.getResult());
    return true;
  }

  /**
   * @param ask the spend authorizing key,to generate ak, 32 bytes
   * @return ak 32 bytes
   */
  public static byte[] librustzcashAskToAk(byte[] ask) throws ZksnarkException {
    LibrustzcashParam.valid32Params(ask);
    byte[] ak = new byte[32];
    INSTANCE.librustzcashAskToAk(ask, ak);
    return ak;
  }

  /**
   * @param nsk the proof authorizing key, to generate nk, 32 bytes
   * @return 32 bytes
   */
  public static byte[] librustzcashNskToNk(byte[] nsk) throws ZksnarkException {
    LibrustzcashParam.valid32Params(nsk);
    byte[] nk = new byte[32];
    INSTANCE.librustzcashNskToNk(nsk, nk);
    return nk;
  }

  // void librustzcash_nsk_to_nk(const unsigned char *nsk, unsigned char *result);

  /**
   * @return r: random number, less than r_J,   32 bytes
   */
  public static byte[] librustzcashSaplingGenerateR(byte[] r) throws ZksnarkException {
    LibrustzcashParam.valid32Params(r);
    INSTANCE.librustzcashSaplingGenerateR(r);
    return r;
  }

  public static boolean librustzcashSaplingKaDerivepublic(KaDerivepublicParams params) {
    return INSTANCE.librustzcashSaplingKaDerivepublic(params.getDiversifier(), params.getEsk(),
        params.getResult());
  }

  public static long librustzcashSaplingProvingCtxInit() {
    return INSTANCE.librustzcashSaplingProvingCtxInit();
  }

  /**
   * check validity of d
   *
   * @param d 11 bytes
   */
  public static boolean librustzcashCheckDiversifier(byte[] d) throws ZksnarkException {
    LibrustzcashParam.valid11Params(d);
    return INSTANCE.librustzcashCheckDiversifier(d);
  }

  public static boolean librustzcashSaplingSpendProof(SpendProofParams params) {
    return INSTANCE.librustzcashSaplingSpendProof(params.getCtx(), params.getAk(),
        params.getNsk(), params.getD(), params.getR(), params.getAlpha(), params.getValue(),
        params.getAnchor(), params.getVoucherPath(), params.getCv(), params.getRk(),
        params.getZkproof());
  }

  public static boolean librustzcashSaplingOutputProof(OutputProofParams params) {
    return INSTANCE.librustzcashSaplingOutputProof(params.getCtx(), params.getEsk(),
        params.getD(), params.getPkD(), params.getR(), params.getValue(), params.getCv(),
        params.getZkproof());
  }

  public static boolean librustzcashSaplingSpendSig(SpendSigParams params) {
    return INSTANCE.librustzcashSaplingSpendSig(params.getAsk(), params.getAlpha(),
        params.getSigHash(), params.getResult());
  }

  public static boolean librustzcashSaplingBindingSig(BindingSigParams params) {
    return INSTANCE.librustzcashSaplingBindingSig(params.getCtx(),
        params.getValueBalance(), params.getSighash(), params.getResult());
  }

  /**
   * convert value to 32-byte scalar
   *
   * @param value 64 bytes
   * @param data 32 bytes
   */
  public static void librustzcashToScalar(byte[] value, byte[] data) throws ZksnarkException {
    LibrustzcashParam.validParamLength(value, 64);
    LibrustzcashParam.valid32Params(data);
    INSTANCE.librustzcashToScalar(value, data);
  }

  public static void librustzcashSaplingProvingCtxFree(long ctx) {
    INSTANCE.librustzcashSaplingProvingCtxFree(ctx);
  }

  public static long librustzcashSaplingVerificationCtxInit() {
    return INSTANCE.librustzcashSaplingVerificationCtxInit();
  }

  public static boolean librustzcashSaplingCheckSpend(CheckSpendParams params) {
    return INSTANCE.librustzcashSaplingCheckSpend(params.getCtx(), params.getCv(),
        params.getAnchor(), params.getNullifier(), params.getRk(), params.getZkproof(),
        params.getSpendAuthSig(), params.getSighashValue());
  }

  public static boolean librustzcashSaplingCheckOutput(CheckOutputParams params) {
    return INSTANCE.librustzcashSaplingCheckOutput(params.getCtx(), params.getCv(),
        params.getCm(), params.getEphemeralKey(), params.getZkproof());
  }

  public static boolean librustzcashSaplingFinalCheck(FinalCheckParams params) {
    return INSTANCE.librustzcashSaplingFinalCheck(params.getCtx(),
        params.getValueBalance(), params.getBindingSig(), params.getSighashValue());
  }

  public static boolean librustzcashSaplingCheckSpendNew(CheckSpendNewParams params) {
    return INSTANCE.librustzcashSaplingCheckSpendNew(params.getCv(),
        params.getAnchor(), params.getNullifier(), params.getRk(), params.getZkproof(),
        params.getSpendAuthSig(), params.getSighashValue());
  }

  public static boolean librustzcashSaplingCheckOutputNew(CheckOutputNewParams params) {
    return INSTANCE.librustzcashSaplingCheckOutputNew(params.getCv(), params.getCm(),
        params.getEphemeralKey(), params.getZkproof());
  }

  public static boolean librustzcashSaplingFinalCheckNew(FinalCheckNewParams params) {
    return INSTANCE
        .librustzcashSaplingFinalCheckNew(params.getValueBalance(), params.getBindingSig(),
            params.getSighashValue(), params.getSpendCv(), params.getSpendCvLen(),
            params.getOutputCv(), params.getOutputCvLen());
  }

  public static void librustzcashSaplingVerificationCtxFree(long ctx) {
    INSTANCE.librustzcashSaplingVerificationCtxFree(ctx);
  }

  public static boolean librustzcashIvkToPkd(IvkToPkdParams params) {
    return INSTANCE.librustzcashIvkToPkd(params.getIvk(), params.getD(), params.getPkD());
  }

  public static void librustzcashMerkleHash(MerkleHashParams params) {
    INSTANCE.librustzcashMerkleHash(params.getDepth(), params.getA(), params.getB(),
        params.getResult());
  }

  /**
   * @param result uncommitted value, 32 bytes
   */
  public static void librustzcashTreeUncommitted(byte[] result) throws ZksnarkException {
    LibrustzcashParam.valid32Params(result);
    INSTANCE.librustzcashTreeUncommitted(result);
  }
}
