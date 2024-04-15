package jin.payment.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.dao.PaymentDAO;
import jin.payment.dto.PaymentDTO;
import jin.payment.handler.PaymentHandlerAdapter;

public class PaymentHistorySelectController implements Controller {

	private static Log log = LogFactory.getLog(PaymentSelectController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		// 사용자 아이디 파라미터 가져오기->로그인 연동할때 세션으로 수정
		String user_id = request.getParameter("user_id");

		PaymentDAO paymentDAO = new PaymentDAO();
		PaymentDTO paymentDTO = new PaymentDTO();

		log.info(paymentDTO);

		ArrayList<PaymentDTO> arrayList = new ArrayList<PaymentDTO>();

		// 사용자 아이디와 일치하는 결제 내역 전체 가져오기
		arrayList = paymentDAO.paymentHistorySelectAllByUserId(user_id);
		log.info(arrayList);

		request.setAttribute("paymentList", arrayList);

		PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();
		log.info("결제 내역 조회");

		// 포워드로 파라미터를 전송한다.
		paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_history_select_view.jsp");
		return paymentHandlerAdapter;
	}

}
