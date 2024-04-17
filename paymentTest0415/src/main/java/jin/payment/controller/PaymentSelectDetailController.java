package jin.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.dao.PaymentDAO;
import jin.payment.dto.PaymentDTO;
import jin.payment.handler.PaymentHandlerAdapter;

//주어진 user_id에 해당하는 결제 상세 정보를 조회
public class PaymentSelectDetailController implements Controller {

	private static Log log = LogFactory.getLog(PaymentSelectDetailController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
//		user_id를 파라미터로 받아옴
		String user_id = request.getParameter("user_id");

//		새로운 PaymentDTO 객체를 생성
		PaymentDTO paymentDTO = new PaymentDTO();

//		조회할 사용자의 아이디를 PaymentDTO 객체에 설정
		paymentDTO.setUser_id(user_id);

//		결제 정보를 조회하기 위해 PaymentDAO 객체를 생성
		PaymentDAO paymentDAO = new PaymentDAO();

//		멤버십 결제 상세 조회
//		설정된 사용자 아이디를 기반으로 결제 정보를 상세 조회하고, 결과를 다시 PaymentDTO 객체에 저장
		paymentDTO = paymentDAO.paymentSelect(paymentDTO);

		log.info(paymentDTO);

//		결제 정보를 보여줄 뷰로 이동하기 위해 PaymentHandlerAdapter 객체를 생성
		PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();

		// 조회된 멤버십 정보를 JSP 파일에 전달
		request.setAttribute("paymentDTO", paymentDTO);

		// 멤버십 정보 페이지로 이동
		paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_select_detail_view.jsp");

//		PaymentHandlerAdapter 객체를 반환
		return paymentHandlerAdapter;
	}
}
