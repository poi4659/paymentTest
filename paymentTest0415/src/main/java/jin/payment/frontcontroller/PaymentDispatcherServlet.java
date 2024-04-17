package jin.payment.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.controller.PaymentDeleteController;
import jin.payment.controller.PaymentInsertController;
import jin.payment.controller.PaymentSelectController;
import jin.payment.controller.PaymentSelectDetailController;
import jin.payment.controller.PaymentHistorySelectController;
import jin.payment.controller.PaymentUpdateController;
import jin.payment.handler.PaymentHandlerAdapter;


/**
 * Servlet implementation class PaymentDispatcherServlet
 */
@WebServlet("/PaymentDispatcherServlet")
public class PaymentDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Log log = LogFactory.getLog(PaymentDispatcherServlet.class);

//	HTTP GET 또는 POST 요청을 처리. 클라이언트의 모든 요청을 처리
//	서블릿이 클라이언트로부터 받은 HTTP 요청 URL 처리
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
		
//		현재 요청된 URI를 반환
		String requestURI = request.getRequestURI();

//		웹 애플리케이션의 컨텍스트 경로를 반환
		String contextPath = request.getContextPath();
		
		/*
		 * 요청 URI에서 컨텍스트 경로를 제외한 부분을 추출함
		 * ->실제 서블릿 매핑명을 얻을 수 있음
		 */
		String pathURL = requestURI.substring(contextPath.length());
		
//		서블릿 매핑명을 로그에 출력
		log.info("매핑명 조회 - " + pathURL);
		
//		서블릿에서 사용할 DeptHandlerAdapter 객체를 초기화
		PaymentHandlerAdapter paymentHandlerAdapter = null;
		
//		서블릿에서 사용할 Controller 객체를 초기화
		Controller controller = null;
		
//		멤버십 결제 메인 페이지
		
		if (pathURL.equals("/PaymentSelect.pay")) {
			controller = new PaymentSelectController();

			paymentHandlerAdapter = controller.execute(request, response);
			
//			결제 정보 조회 확인-서버의 로그 파일에 기록됨
			log.info("결제 정보 조회 확인 - " + paymentHandlerAdapter);
			
		}	
		
//		멤버십 결제 상세 페이지
		else if (pathURL.equals("/PaymentSelectDetail.pay")) {
			controller = new PaymentSelectDetailController();
			
			paymentHandlerAdapter = controller.execute(request, response);
			
//			결제 정보 조회 확인-서버의 로그 파일에 기록됨
			log.info("결제 정보 상세 조회 확인 - " + paymentHandlerAdapter);
			
		}	
		
//		멤버십 결제 등록 뷰 확인
		else if (pathURL.equals("/PaymentInsertView.pay")) {
			paymentHandlerAdapter = new PaymentHandlerAdapter();

//			멤버십 결제 등록 뷰 확인-서버의 로그 파일에 기록됨
			paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_insert.jsp");
			log.info("멤버십 결제 등록 뷰 확인 - " + paymentHandlerAdapter);
			
		}
		
//		멤버십 결제 등록 확인
		else if (pathURL.equals("/PaymentInsert.pay")) {
			controller = new PaymentInsertController();

			paymentHandlerAdapter = controller.execute(request, response);
			
//			멤버십 결제 등록 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 결제 등록 성공 확인 - " + paymentHandlerAdapter);
			
		}
		
		
//		멤버십 결제 수정 뷰 확인
		else if (pathURL.equals("/PaymentUpdateView.pay")) {
			paymentHandlerAdapter = new PaymentHandlerAdapter();

//			멤버십 결제 수정 뷰 확인-서버의 로그 파일에 기록됨
			paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_update.jsp");
			log.info("멤버십 결제 수정 뷰 확인 - " + paymentHandlerAdapter);
			
		}
		
//		멤버십 결제 수정 확인
		else if (pathURL.equals("/PaymentUpdate.pay")) {
			controller = new PaymentUpdateController();

			paymentHandlerAdapter = controller.execute(request, response);
			
//			멤버십 결제 수정 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 결제 수정 성공 확인 - " + paymentHandlerAdapter);
			
		}
		
		
//		멤버십 결제 삭제 뷰 확인
		else if (pathURL.equals("/PaymentDeleteView.pay")) {
			paymentHandlerAdapter = new PaymentHandlerAdapter();

//			멤버십 결제 삭제 뷰 확인-서버의 로그 파일에 기록됨
			paymentHandlerAdapter.setPath("/WEB-INF/view/payment/payment_delete.jsp");
			log.info("멤버십 결제 삭제 뷰 확인 - " + paymentHandlerAdapter);
			
		}
		
//		멤버십 결제 삭제 확인
		else if (pathURL.equals("/PaymentDelete.pay")) {
			controller = new PaymentDeleteController();

			paymentHandlerAdapter = controller.execute(request, response);
			
//			멤버십 결제 삭제 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 결제 삭제 성공 확인 - " + paymentHandlerAdapter);
			
		}
		
		
//		결제 내역 전체 조회
		else if (pathURL.equals("/PaymentHistory.pay")) {
			controller = new PaymentHistorySelectController();

			paymentHandlerAdapter = controller.execute(request, response);
			
//			결제 내역 조회 확인-서버의 로그 파일에 기록됨
			log.info("결제 내역 조회 확인 - " + paymentHandlerAdapter);
			
		}
		
//		isRedirect 메서드 값이 false이면 포워드 방식으로 처리하고 true면 리다이렉트로 처리
		if (paymentHandlerAdapter != null) {
			if (paymentHandlerAdapter.isRedirect()) {
//				리다이렉트인 경우 response.sendRedirect()를 사용하여 새로운 URL로 이동
				response.sendRedirect(paymentHandlerAdapter.getPath());
			} else {
//				포워드인 경우 RequestDispatcher를 사용하여 요청과 응답을 해당 JSP 파일로 전달
				RequestDispatcher dispatcher = request.getRequestDispatcher(paymentHandlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
		
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}

