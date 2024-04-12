package jin.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.dao.PaymentDAO;
import jin.payment.dto.PaymentDTO;
import jin.payment.handler.PaymentHandlerAdapter;

public class PaymentSelectController implements Controller {

	private static Log log = LogFactory.getLog(PaymentSelectController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		
		PaymentDAO paymentDAO = new PaymentDAO();
		
//		사용자 아이디 조회 
		PaymentDTO paymentDTO = paymentDAO.getPaymentByUserId(user_id);

		log.info(paymentDTO);

		PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();

		if (paymentDTO == null) {
			// 멤버십에 가입하지 않은 경우 가입 페이지 이동할 수 있는 페이지로 이동
			paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_insert.jsp");
		} else {
			// 조회된 멤버십 정보를 JSP 파일에 전달
			request.setAttribute("paymentDTO", paymentDTO);
			paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_select_view.jsp"); // 멤버십 정보 페이지로 이동

//          NullPointerException 방지
			if (paymentDTO != null) {
				request.setAttribute("user_id", paymentDTO.getUser_id());
				request.setAttribute("membership_grade", paymentDTO.getMembership_grade());
				request.setAttribute("payment_date", paymentDTO.getPayment_date());
				request.setAttribute("payment_method", paymentDTO.getPayment_method());
				request.setAttribute("payment_price", paymentDTO.getPayment_price());
			}
			log.info(paymentDTO);
		}

		return paymentHandlerAdapter;
	}
}
