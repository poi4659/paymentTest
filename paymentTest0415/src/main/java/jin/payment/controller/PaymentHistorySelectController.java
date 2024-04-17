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

//주어진 사용자 아이디에 해당하는 결제 내역을 전체 조회
public class PaymentHistorySelectController implements Controller {

	private static Log log = LogFactory.getLog(PaymentSelectController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		// 사용자 아이디 파라미터 가져오기->로그인 연동할때 세션으로 수정
		String user_id = request.getParameter("user_id");

//		결제 정보를 조회하기 위해 PaymentDAO 객체를 생성
		PaymentDAO paymentDAO = new PaymentDAO();
		
//		결제 내역을 담을 PaymentDTO 객체를 생성
		PaymentDTO paymentDTO = new PaymentDTO();
		
//		로그에 결제 내역을 기록
		log.info(paymentDTO);

//		결제 내역을 저장할 ArrayList 객체를 생성
		ArrayList<PaymentDTO> arrayList = new ArrayList<PaymentDTO>();

//		사용자 아이디와 일치하는 결제 내역을 데이터베이스에서 가져와 ArrayList에 저장
		arrayList = paymentDAO.paymentHistorySelectAllByUserId(user_id);
		
//		가져온 결제 내역을 로그에 기록
		log.info(arrayList);

//		결제 내역을 request에 속성으로 설정
		request.setAttribute("paymentList", arrayList);

//		PaymentHandlerAdapter 객체를 생성하여 설정된 경로를 지정하고 반환
		PaymentHandlerAdapter paymentHandlerAdapter = new PaymentHandlerAdapter();
		log.info("결제 내역 조회");

		// 포워드로 파라미터를 전송한다.
		paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_history_select_view.jsp");
		return paymentHandlerAdapter;
	}

}
