package jin.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.dao.PaymentDAO;
import jin.payment.dto.PaymentDTO;
import jin.payment.handler.PaymentHandlerAdapter;

public class PaymentDeleteController implements Controller{

	private static Log log = LogFactory.getLog(PaymentDeleteController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		log.info("아이디 : " + user_id);
		
		PaymentDAO paymentDAO = new PaymentDAO();
		PaymentDTO paymentDTO = new PaymentDTO();

// 		입력한 정보를 DeptDTO 클래스의 인스턴스에 저장한다.
		paymentDTO.setUser_id(user_id);
		
// 		속성에 DTO 객체를 속성값으로 저장한다.
		request.setAttribute("paymentDTO", paymentDTO);
		
//      멤버십 결제 정보 payment 테이블에서 삭제
		paymentDTO = paymentDAO.paymentDelete(paymentDTO);
		log.info("멤버십 결제 정보 삭제 : " + paymentDTO);
		
//      멤버십 결제 내역 정보 payment_history 테이블에서 삭제
        paymentDAO.paymentHistoryDelete(paymentDTO);
		
		PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();
		
// 		포워드로 파라미터를 전송한다.
		paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_delete_view.jsp");
		return paymentHandlerAdapter;
	}
}

