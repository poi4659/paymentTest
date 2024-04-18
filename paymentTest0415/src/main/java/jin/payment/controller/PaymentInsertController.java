package jin.payment.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import jin.payment.control.Controller;
import jin.payment.dao.PaymentDAO;
import jin.payment.dto.PaymentDTO;
import jin.payment.handler.PaymentHandlerAdapter;

//사용자가 결제 정보를 입력하고 제출했을 때, 해당 정보를 데이터베이스에 저장
public class PaymentInsertController implements Controller{
	private static Log log = LogFactory.getLog(PaymentInsertController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
//		사용자가 입력한 폼 데이터 받아오기
        String user_id = request.getParameter("user_id");
        String membership_grade = request.getParameter("membership_grade");
        String payment_method = request.getParameter("payment_method");
        int payment_price = 0;
        
//      멤버십 등급에 따른 결제 금액 설정
        if ("Gold".equals(membership_grade)) {
            payment_price = 10000;
        } else if ("Silver".equals(membership_grade)) {
        	payment_price = 7000;
        }
        
//      현재 날짜와 시간을 가져와서 형식을 변환한 후 문자열로 저장 (Java 8 이상)
        LocalDateTime payment_date = LocalDateTime.now();

//      LocalDateTime을 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = payment_date.format(formatter);
        
//      결제 정보를 담는 PaymentDTO 객체를 생성
        PaymentDTO paymentDTO = new PaymentDTO();
        
//      멤버십 정보를 paymentDTO 객체에 저장 (사용자 아이디는 나중에 로그인 연동 시 처리할 예정)
        paymentDTO.setUser_id(user_id);
        paymentDTO.setMembership_grade(membership_grade);
        
//      현재 날짜를 문자열로 저장
        paymentDTO.setPayment_date(formattedDate); 
        
        paymentDTO.setPayment_method(payment_method);
        paymentDTO.setPayment_price(payment_price);
        
//      결제 정보를 로그에 기록
        log.info("멤버십 결제 등록 : " + paymentDTO);
       
//      결제 정보를 저장하기 위해 PaymentDAO 객체를 생성
        PaymentDAO paymentDAO = new PaymentDAO();
        
//      멤버십 결제 정보 payment 테이블에 저장
        paymentDTO = paymentDAO.paymentInsert(paymentDTO);
        
//      멤버십 결제 내역 정보 payment_history 테이블에 저장
        paymentDAO.paymentHistoryInsert(paymentDTO);
        
//      저장된 결제 정보를 request에 속성으로 설정
        request.setAttribute("paymentDTO", paymentDTO);
        
        PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();
		
//		JSP 파일의 경로를 설정한 후 paymentHandlerAdapter 객체 생성하여 반환
        paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_insert_view.jsp");
		
		return paymentHandlerAdapter;
	}
}
