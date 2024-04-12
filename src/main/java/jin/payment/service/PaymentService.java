package jin.payment.service;

import java.util.ArrayList;

import jin.payment.dto.PaymentDTO;

public interface PaymentService {
//	전체 결제 정보 조회
	public ArrayList<PaymentDTO> paymentSelectAll();
	
//	특정 사용자 아이디로 전체 결제 내역 정보 조회-payment_history 테이블에서 조회
	public ArrayList<PaymentDTO> paymentHistorySelectAllByUserId(String userId);
	
//	멤버십 결제 정보 상세 조회
	public PaymentDTO paymentSelect(PaymentDTO paymentDTO);
	
//	결제 정보 저장
	public PaymentDTO paymentInsert(PaymentDTO paymentDTO);

//	결제 정보 수정
	public PaymentDTO paymentUpdate(PaymentDTO paymentDTO);
	
//	결제 정보 삭제
	public PaymentDTO paymentDelete(PaymentDTO paymentDTO);

//	결제 내역 정보 저장
	public PaymentDTO paymentHistoryInsert(PaymentDTO paymentDTO);

//	결제 내역 정보 삭제
	public PaymentDTO paymentHistoryDelete(PaymentDTO paymentDTO);
}
