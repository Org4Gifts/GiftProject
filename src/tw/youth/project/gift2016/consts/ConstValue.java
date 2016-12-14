package tw.youth.project.gift2016.consts;

public class ConstValue {
	// 通用常數
	public static final String PERMISSION_NOT_ENOUGH = "權限不足";

	//Mail專用常數
	public static final String SUBJECT = "密碼通知信函";
	public static final String MSG = "親愛的使用者您好，這是忘記密碼通知信函。\n"
			+ "您的密碼修改連結為：%s";
	
	// Login 使用的常數
	public static final String LOGIN_SUCCESS = "登入成功";
	public static final String LOGIN_FAILURE = "登入失敗";
	public static final String LOGIN_NOT_LOGIN = "您尚未登入";
	public static final String LOGIN_OLD_PASS_ERROR = "舊密碼不正確，請重新操作";
	public static final String LOGIN_NEW_PASS_ERROR = "新密碼驗證失敗，請重新操作";
	public static final String LOGIN_SEND_EMAIL_SUCCESS = "已發送電子郵件至您的信箱 ";
	public static final String LOGIN_CHECK_EMAIL_FAILURE = "查無此電子郵件，請確認";

	// Orders 使用的常數
	public static final String ORDERS_STATUS_PREPARING = "Preparing";
	public static final String ORDERS_STATUS_PROCESSING = "Processing";
	public static final String ORDERS_STATUS_APPROVED = "Approved";
	public static final String OREDERS_STATUS_COMPLETED = "Completed";
	public static final String ORDERS_STATUS_OBSOLETED = "Obsoleted";
	public static final String ORDERS_STATUS_REJECTED = "Rejected";
	public static final String ORDERS_STATUS_CANCEL = "Cancel";
	// Status0 : Rejected (單據被退件)
	// Status1 : Preparing (單據準備中，尚未送出)
	// STatus2 : Processing (單據被送出，待簽核中)
	// Status8 : Approved (簽核流程結束)
	// Status9 : Completed (行政部門結案，禮品已轉出，系統自動扣除 【及時庫存量】)
	// Status10 : Obsoleted (行政部門作廢，系統自動加回 【及時庫存量】)
	// Status11 : Cancel (取消訂單)

	public static final String ORDERS_ADD_FAILURE = "建立失敗";
	public static final String ORDERS_ADD_AODR_SUCCESS = "訂單建立成功";
	public static final String ORDERS_ADD_AIO_SUCCESS = "調撥單建立成功";

	public static final String ORDERS_UPDATE_FAILURE = "修改失敗";
	public static final String ORDERS_UPDATE_AODR_SUCCESS = "訂單修改成功";
	public static final String ORDERS_UPDATE_AIO_SUCCESS = "調撥單修改成功";

	public static final String ORDERS_CANCEL_FAILURE = "取消失敗";
	public static final String ORDERS_CANCEL_AODR_SUCCESS = "訂單取消成功";
	public static final String ORDERS_CANCEL_AIO_SUCCESS = "調撥單取消成功";

	public static final String ORDERS_SUBMIT_FAILURE = "發送失敗";
	public static final String ORDERS_SUBMIT_AODR_SUCCESS = "訂單發送成功";
	public static final String ORDERS_SUBMIT_AIO_SUCCESS = "調撥單發送成功";

	public static final String ORDERS_DROP_FAILURE = "訂單副檔刪除失敗";
	public static final String ORDERS_DROP_AODRDT_SUCCESS = "訂單副檔刪除成功";
	public static final String ORDERS_DROP_AIODT_SUCCESS = "調撥單副檔刪除成功";

	// Signatures 使用的常數
	public static final String SIGNATURE_STATUS_SEND = "Send";
	public static final String SIGNATURE_STATUS_NONE = "None";
	public static final String SIGNATURE_STATUS_APPROVE = "Approve";
	public static final String SIGNATURE_STATUS_REJECT = "Reject";
	public static final String SIGNATURE_STATUS_COMPLETE = "Complete";
	// 訂單簽核狀態 Send 發送請求者 ,None 尚未簽核 ,Approve 同意 ,Reject 拒絕 ,Complete 完成
	
	
}
