package jin.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.dto.PaymentDTO;
import jin.payment.service.PaymentService;

public class PaymentDAO implements PaymentService {

	private static final Log log = LogFactory.getLog(PaymentDAO.class);

//	멤버십 결제 정보 상세 조회
	@Override
	public PaymentDTO paymentSelect(PaymentDTO paymentDTO) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            // 데이터베이스 연결
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

//          쿼리 문제 없음
            String sql = "select user_id, membership_grade, to_char(payment_date, 'yyyy-mm-dd') payment_date, payment_method, payment_price from payment";
            sql += " where user_id = ? ";

        	preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1, paymentDTO.getUser_id( ));
            
            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            // 조회 결과 처리
            if (resultSet.next()) {
            	// 멤버십 정보 생성 및 설정
//            	log.info("아이디 확인 - " + resultSet.getString("user_id"));
            	paymentDTO.setUser_id(resultSet.getString("user_id"));
            	paymentDTO.setMembership_grade(resultSet.getString("membership_grade"));
            	paymentDTO.setPayment_date(resultSet.getString("payment_date"));
            	paymentDTO.setPayment_method(resultSet.getString("payment_method"));
            	paymentDTO.setPayment_price(resultSet.getInt("payment_price"));
                
            }
        } catch (Exception e) {
            log.error("멤버십 결제 정보 상세 조회 실패: " + e.getMessage());
        } finally {
            // 리소스 해제
            try {
            	resultSet.close( );
				preparedStatement.close( );
				connection.close( );
            } catch (SQLException e) {
                log.error("리소스 해제 실패: " + e.getMessage());
            }
        }

        return paymentDTO;
    }

	
//  사용자 아이디 조회
    public PaymentDTO getPaymentByUserId(String user_id) {
    	PaymentDTO paymentDTO = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 데이터베이스 연결
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            // SQL 쿼리 작성
            String sql = "SELECT * FROM payment WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_id);

            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            // 조회 결과 처리
            if (resultSet.next()) {
            	// 멤버십 결제 정보 생성 및 설정
            	paymentDTO = new PaymentDTO();
            	paymentDTO.setUser_id(resultSet.getString("user_id"));
            	paymentDTO.setMembership_grade(resultSet.getString("membership_grade"));
            	paymentDTO.setPayment_method(resultSet.getString("payment_method"));
            	paymentDTO.setPayment_price(resultSet.getInt("payment_price"));
                
            }
        } catch (Exception e) {
            log.error("멤버십 정보 조회 실패: " + e.getMessage());
        } finally {
            // 리소스 해제
            try {
            	resultSet.close( );
				preparedStatement.close( );
				connection.close( );
            } catch (SQLException e) {
                log.error("리소스 해제 실패: " + e.getMessage());
            }
        }

        return paymentDTO;
    }
	
//	특정 사용자 아이디에 해당하는 결제 내역 전체 조회-payment_history 테이블
	public ArrayList<PaymentDTO> paymentHistorySelectAllByUserId(String userId) {
	    ArrayList<PaymentDTO> paymentList = new ArrayList<>();
	    ResultSet resultSet = null;
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        // JNDI를 사용하여 데이터 소스 가져오기
	        Context context = new InitialContext();
	        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
	        connection = dataSource.getConnection();
	        
			String sql = "select payment_num, user_id, membership_grade, to_char(payment_date, 'yyyy-mm-dd') payment_date, payment_method, payment_price from payment_history";
	        sql += " where user_id = ? ";
	        log.info("SQL 확인 - " + sql);
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, userId);
	        
	        // 쿼리 실행
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            PaymentDTO paymentDTO = new PaymentDTO();
	            paymentDTO.setPayment_num(resultSet.getInt("payment_num"));
	            paymentDTO.setUser_id(resultSet.getString("user_id"));
	            paymentDTO.setMembership_grade(resultSet.getString("membership_grade"));
	            paymentDTO.setPayment_date(resultSet.getString("payment_date"));
	            paymentDTO.setPayment_method(resultSet.getString("payment_method"));
	            paymentDTO.setPayment_price(resultSet.getInt("payment_price"));
	            
	            paymentList.add(paymentDTO);
	        }

	        if (paymentList.isEmpty()) {
	            log.info("결제 내역이 없습니다.");
	        }
	    } catch (Exception e) {
	        log.info("결제 내역 조회 실패 - " + e);
	    } finally {
	        try {
	            resultSet.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return paymentList;
	}

//	멤버십 결제 정보 전체 조회
	@Override
	public ArrayList<PaymentDTO> paymentSelectAll() {
		ArrayList<PaymentDTO> arrayList = new ArrayList<PaymentDTO>();
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// JNDI를 사용하여 데이터 소스 가져오기
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
	
			
			String sql = "select payment_num, user_id, to_char(payment_date, 'yyyy-mm-dd') payment_date, payment_method, payment_price from payment";
			log.info("SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			
//			SQL 문인 select…from…where 문을 실행하고 데이터를 조회한다.
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				PaymentDTO paymentDTO = new PaymentDTO();
				paymentDTO.setPayment_num(resultSet.getInt("payment_num"));
				paymentDTO.setUser_id(resultSet.getString("user_id"));
				paymentDTO.setPayment_date(resultSet.getString("payment_date"));
				paymentDTO.setPayment_method(resultSet.getString("payment_method"));
				paymentDTO.setPayment_price(resultSet.getInt("payment_price"));
				
//				저장한 정보를 DeptDTO 클래스의 인스턴스에 추가한다.
				arrayList.add(paymentDTO);
			}
			
//			현재 행 번호를 검색한다.
			resultSet.getRow();
			if (resultSet.getRow() == 0) {
				log.info("결제 내역이 없습니다.");
			}
			
		} catch (Exception e) {
			log.info("결제 내역 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

//	멤버십 결제 정보 등록
	@Override
	public PaymentDTO paymentInsert(PaymentDTO paymentDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// JNDI를 사용하여 데이터 소스 가져오기
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "INSERT INTO payment (payment_num, user_id, membership_grade, payment_date, payment_method, payment_price) ";
			sql += "VALUES (payment_seq.NEXTVAL, ?, ?, sysdate, ?, ?)";

			log.info("SQL 확인 - " + sql);

			// PreparedStatement 객체 생성
			preparedStatement = connection.prepareStatement(sql);

			// 파라미터 설정
			preparedStatement.setString(1, paymentDTO.getUser_id());
			preparedStatement.setString(2, paymentDTO.getMembership_grade());
			preparedStatement.setString(3, paymentDTO.getPayment_method());
			preparedStatement.setInt(4, paymentDTO.getPayment_price());

			int count = preparedStatement.executeUpdate();

			if (count > 0) {
				// 자동 커밋 해제
				connection.setAutoCommit(false);
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}

		} catch (Exception e) {
			log.info("결제 내역 저장 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return paymentDTO;

	}

//	결제 내역 정보 등록-payment_history 테이블
	@Override
	public PaymentDTO paymentHistoryInsert(PaymentDTO paymentDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// JNDI를 사용하여 데이터 소스 가져오기
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "INSERT INTO payment_history (payment_num, user_id, membership_grade, payment_date, payment_method, payment_price) ";
			sql += "VALUES (payment_history_seq.NEXTVAL, ?, ?, sysdate, ?, ?)";

			log.info("SQL 확인 - " + sql);

			// PreparedStatement 객체 생성
			preparedStatement = connection.prepareStatement(sql);

			// 파라미터 설정
			preparedStatement.setString(1, paymentDTO.getUser_id());
			preparedStatement.setString(2, paymentDTO.getMembership_grade());
			preparedStatement.setString(3, paymentDTO.getPayment_method());
			preparedStatement.setInt(4, paymentDTO.getPayment_price());

			int count = preparedStatement.executeUpdate();

			if (count > 0) {
				// 자동 커밋 해제
				connection.setAutoCommit(false);
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}

		} catch (Exception e) {
			log.info("결제 내역 저장 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return paymentDTO;

	}
	
	
//	멤버십 결제 정보 수정
	@Override
	public PaymentDTO paymentUpdate(PaymentDTO paymentDTO) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JNDI를 사용하여 데이터 소스 가져오기
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            // SQL 쿼리 작성-디벨로퍼에서 확인했는데 문제 없음
            String sql = "UPDATE payment SET membership_grade = ?, payment_method = ?, payment_price = ? WHERE user_id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, paymentDTO.getMembership_grade());

            preparedStatement.setString(2, paymentDTO.getPayment_method());
            preparedStatement.setInt(3, paymentDTO.getPayment_price());
            preparedStatement.setString(4, paymentDTO.getUser_id());

            // 쿼리 실행
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                // 자동 커밋 해제
                connection.setAutoCommit(false);
                connection.commit();
                log.info("커밋되었습니다.");
            } else {
                connection.rollback();
                log.info("롤백되었습니다.");
            }

        } catch (Exception e) {
            log.error("멤버십 결제 정보 수정 실패 - " + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return paymentDTO;
    }


//	멤버십 결제 정보 삭제
	@Override
	public PaymentDTO paymentDelete(PaymentDTO paymentDTO) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JNDI를 사용하여 데이터 소스 가져오기
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            String sql = "delete from payment ";
			sql += " where user_id = ? ";
			log.info("SQL - " + sql);
			
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, paymentDTO.getUser_id());

            // 쿼리 실행
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                // 자동 커밋 해제
                connection.setAutoCommit(false);
                connection.commit();
                log.info("커밋되었습니다.");
            } else {
                connection.rollback();
                log.info("롤백되었습니다.");
            }

        } catch (Exception e) {
            log.error("멤버십 결제 정보 삭제 실패 - " + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return paymentDTO;
    }

//	멤버십 결제 내역 삭제
	@Override
	public PaymentDTO paymentHistoryDelete(PaymentDTO paymentDTO) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JNDI를 사용하여 데이터 소스 가져오기
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            String sql = "delete from payment_history ";
			sql += " where user_id = ? ";
			log.info("SQL - " + sql);
			
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, paymentDTO.getUser_id());

            // 쿼리 실행
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                // 자동 커밋 해제
                connection.setAutoCommit(false);
                connection.commit();
                log.info("커밋되었습니다.");
            } else {
                connection.rollback();
                log.info("롤백되었습니다.");
            }

        } catch (Exception e) {
            log.error("멤버십 결제 내역 삭제 실패 - " + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return paymentDTO;
    }

}
