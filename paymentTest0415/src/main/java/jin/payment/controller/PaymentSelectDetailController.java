package jin.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.dao.PaymentDAO;
import jin.payment.dto.PaymentDTO;
import jin.payment.handler.PaymentHandlerAdapter;

public class PaymentSelectDetailController implements Controller{

	 private static Log log = LogFactory.getLog(PaymentSelectDetailController.class);

	    
	    @Override
	    public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
	        String user_id = request.getParameter("user_id");
	        
	        PaymentDTO paymentDTO = new PaymentDTO();
	        
	        // 사용자 아이디 설정
	        paymentDTO.setUser_id(user_id);

	        PaymentDAO paymentDAO = new PaymentDAO();
	        
	        // 멤버십 결제 상세 조회
	        paymentDTO = paymentDAO.paymentSelect(paymentDTO);
	        
	        log.info(paymentDTO);
	        
	        // MembershipHandlerAdapter 객체 생성
	        PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();

	        // 조회된 멤버십 정보를 JSP 파일에 전달
	        request.setAttribute("paymentDTO", paymentDTO);
	        
	        // 멤버십 정보 페이지로 이동
	        paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_select_detail_view.jsp"); 
	        
	        return paymentHandlerAdapter;
	    }
	}
