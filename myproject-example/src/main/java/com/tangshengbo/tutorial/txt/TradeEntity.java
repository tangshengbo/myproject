package com.tangshengbo.tutorial.txt;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradeEntity implements Serializable {
    /**
     * 唯一主键
     */
    private BigDecimal id;

    /**
     * 商户号
     */
    @TxtTitle("商户号")
    private String merchantId;

    /**
     * 终端号
     */
    @TxtTitle("终端号")
    private String terminalId;

    /**
     * 文件类型(0收款 1付款)
     */
    private String type;

    /**
     * 交易类型
     */
    @TxtTitle("交易类型")
    private String tradeType;

    /**
     * 交易子类型
     */
    @TxtTitle("交易子类型")
    private String subTradeType;

    /**
     * 订单号
     */
    @TxtTitle("订单号")
    private String bfOrderNo;

    /**
     * 商户订单号
     */
    @TxtTitle("商户订单号")
    private String orderNo;

    /**
     * 批次号
     */
    @TxtTitle("批次号")
    private String batchId;

    /**
     * 清算日期
     */
    @TxtTitle("清算日期")
    private String settlementDate;

    /**
     * 订单状态 1-成功 (对账单只记录成功订单)
     */
    @TxtTitle("订单状态")
    private String status;

    /**
     * 交易金额
     */
    @TxtTitle(value = "交易金额")
    private BigDecimal amount;

    /**
     * 手续费
     */
    @TxtTitle(value = "手续费")
    private BigDecimal poundage;

    /**
     * 收款人账号
     */
    private String payeeAccount;

    /**
     * 收款人姓名
     */
    private String payeeName;

    /**
     * 交易号
     */
    @TxtTitle(value = "交易号")
    private String bfTradeNo;

    /**
     * 商户退款订单号
     */
    private String refundOrderNo;

    /**
     * 支付订单创建时间
     */
    @TxtTitle(value = "支付订单创建时间")
    private Date orderPayDate;

    /**
     * 退款订单创建时间
     */
    private Date orderRefundDate;

    /**
     * 对账状态:0 未对账  1 自动对账成功  2 对账失败  3 手动对账成功  4 无需对账
     */
    private String checkFlag;

    /**
     * 对账描述
     */
    private String checkComment;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 唯一主键
     * @return ID 唯一主键
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * 唯一主键
     * @param id 唯一主键
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 商户号
     * @return MERCHANT_ID 商户号
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 商户号
     * @param merchantId 商户号
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    /**
     * 终端号
     * @return TERMINAL_ID 终端号
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * 终端号
     * @param terminalId 终端号
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    /**
     * 文件类型(0收款 1付款)
     * @return TYPE 文件类型(0收款 1付款)
     */
    public String getType() {
        return type;
    }

    /**
     * 文件类型(0收款 1付款)
     * @param type 文件类型(0收款 1付款)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 交易类型
     * @return TRADE_TYPE 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 交易类型
     * @param tradeType 交易类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 交易子类型
     * @return SUB_TRADE_TYPE 交易子类型
     */
    public String getSubTradeType() {
        return subTradeType;
    }

    /**
     * 交易子类型
     * @param subTradeType 交易子类型
     */
    public void setSubTradeType(String subTradeType) {
        this.subTradeType = subTradeType == null ? null : subTradeType.trim();
    }

    /**
     * 订单号
     * @return BF_ORDER_NO 订单号
     */
    public String getBfOrderNo() {
        return bfOrderNo;
    }

    /**
     * 订单号
     * @param bfOrderNo 订单号
     */
    public void setBfOrderNo(String bfOrderNo) {
        this.bfOrderNo = bfOrderNo == null ? null : bfOrderNo.trim();
    }

    /**
     * 商户订单号
     * @return ORDER_NO 商户订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 商户订单号
     * @param orderNo 商户订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 批次号
     * @return BATCH_ID 批次号
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 批次号
     * @param batchId 批次号
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * 清算日期
     * @return SETTLEMENT_DATE 清算日期
     */
    public String getSettlementDate() {
        return settlementDate;
    }

    /**
     * 清算日期
     * @param settlementDate 清算日期
     */
    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate == null ? null : settlementDate.trim();
    }

    /**
     * 订单状态 1-成功 (对账单只记录成功订单)
     * @return STATUS 订单状态 1-成功 (对账单只记录成功订单)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 订单状态 1-成功 (对账单只记录成功订单)
     * @param status 订单状态 1-成功 (对账单只记录成功订单)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 交易金额
     * @return AMOUNT 交易金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 交易金额
     * @param amount 交易金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 手续费
     * @return POUNDAGE 手续费
     */
    public BigDecimal getPoundage() {
        return poundage;
    }

    /**
     * 手续费
     * @param poundage 手续费
     */
    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    /**
     * 收款人账号
     * @return PAYEE_ACCOUNT 收款人账号
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     * 收款人账号
     * @param payeeAccount 收款人账号
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    /**
     * 收款人姓名
     * @return PAYEE_NAME 收款人姓名
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 收款人姓名
     * @param payeeName 收款人姓名
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     * 交易号
     * @return BF_TRADE_NO 交易号
     */
    public String getBfTradeNo() {
        return bfTradeNo;
    }

    /**
     * 交易号
     * @param bfTradeNo 交易号
     */
    public void setBfTradeNo(String bfTradeNo) {
        this.bfTradeNo = bfTradeNo == null ? null : bfTradeNo.trim();
    }

    /**
     * 商户退款订单号
     * @return REFUND_ORDER_NO 商户退款订单号
     */
    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    /**
     * 商户退款订单号
     * @param refundOrderNo 商户退款订单号
     */
    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo == null ? null : refundOrderNo.trim();
    }

    /**
     * 支付订单创建时间
     * @return ORDER_PAY_DATE 支付订单创建时间
     */
    public Date getOrderPayDate() {
        return orderPayDate;
    }

    /**
     * 支付订单创建时间
     * @param orderPayDate 支付订单创建时间
     */
    public void setOrderPayDate(Date orderPayDate) {
        this.orderPayDate = orderPayDate;
    }

    /**
     * 退款订单创建时间
     * @return ORDER_REFUND_DATE 退款订单创建时间
     */
    public Date getOrderRefundDate() {
        return orderRefundDate;
    }

    /**
     * 退款订单创建时间
     * @param orderRefundDate 退款订单创建时间
     */
    public void setOrderRefundDate(Date orderRefundDate) {
        this.orderRefundDate = orderRefundDate;
    }

    /**
     * 对账状态:0 未对账  1 自动对账成功  2 对账失败  3 手动对账成功  4 无需对账
     * @return CHECK_FLAG 对账状态:0 未对账  1 自动对账成功  2 对账失败  3 手动对账成功  4 无需对账
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * 对账状态:0 未对账  1 自动对账成功  2 对账失败  3 手动对账成功  4 无需对账
     * @param checkFlag 对账状态:0 未对账  1 自动对账成功  2 对账失败  3 手动对账成功  4 无需对账
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    /**
     * 对账描述
     * @return CHECK_COMMENT 对账描述
     */
    public String getCheckComment() {
        return checkComment;
    }

    /**
     * 对账描述
     * @param checkComment 对账描述
     */
    public void setCheckComment(String checkComment) {
        this.checkComment = checkComment == null ? null : checkComment.trim();
    }

    /**
     * 创建时间
     * @return CREATE_DATE 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_DATE 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}