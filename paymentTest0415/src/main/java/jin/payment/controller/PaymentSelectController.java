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

		request.setAttribute("paymentDTO", paymentDTO);
		// user_id도 request에 설정
	    request.setAttribute("user_id", user_id);
		paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_select_view.jsp"); // 멤버십 정보 페이지로 이동

//          NullPointerException 방지-멤버십 정보가 null이 아닌 경우에만 해당 정보를 전달
		if (paymentDTO != null) {
			request.setAttribute("user_id", paymentDTO.getUser_id());
			request.setAttribute("membership_grade", paymentDTO.getMembership_grade());
			request.setAttribute("payment_date", paymentDTO.getPayment_date());
			request.setAttribute("payment_method", paymentDTO.getPayment_method());
			request.setAttribute("payment_price", paymentDTO.getPayment_price());
		}
		log.info(paymentDTO);

		return paymentHandlerAdapter;
	}
}
