package jin.payment.dto;

public class PaymentDTO {
	private int payment_num;
	private String user_id;
	private String membership_grade;
    private String payment_date;
    private String payment_method;
    private int payment_price;
    
    
	public int getPayment_num() {
		return payment_num;
	}

	public void setPayment_num(int payment_num) {
		this.payment_num = payment_num;
	}
	

	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public String getMembership_grade() {
		return membership_grade;
	}

	public void setMembership_grade(String membership_grade) {
		this.membership_grade = membership_grade;
	}

	
	public String getPayment_date() {
		return payment_date;
	}
	
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	
	
	public String getPayment_method() {
		return payment_method;
	}
	
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	
	
	public int getPayment_price() {
		return payment_price;
	}
	
	public void setPayment_price(int payment_price) {
		this.payment_price = payment_price;
	}

	
	@Override
	public String toString() {
		return "PaymentDTO [payment_num=" + payment_num + ", user_id=" + user_id + ", membership_grade="
				+ membership_grade + ", payment_date=" + payment_date + ", payment_method=" + payment_method
				+ ", payment_price=" + payment_price + "]";
	}

    
}
