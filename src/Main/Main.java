package Main;

import View.OrdersView;
import View.User.UserLandingPage;

public class Main{
	
	public Main() {
//		UserLandingPage u = new UserLandingPage();
//		u.setVisible(true);
		
		OrdersView ov = new OrdersView();
	}

	public static void main(String[] args) {
		new Main();
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource() == employeeBtn) {
//			eh.viewManageEmployeeForm();
//			this.dispose();
//		}
//		else if (e.getSource() == foodBtn) {
//			fh.viewManageFoodForm();
//			this.dispose();
//		}
//	}

}
