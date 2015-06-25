package model;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class insertCustomer
 */
@WebServlet("/insertCustomer")
public class insertCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("123");
		String FName = request.getParameter("firstname");
        String LName = request.getParameter("lastname");
        String EAddress = request.getParameter("emailaddress");
        String Pwd = request.getParameter("password");
        
        
        EntityManager em =DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        
        try
        {
        	Customer cust = new model.Customer();
        	cust.setFirstName(FName);
        	cust.setLastName(LName);
        	cust.setEmailAddress(EAddress);
        	cust.setPassword(Pwd);
        	

        	em.persist(cust);
        	trans.commit();
        }catch (Exception e){
        	System.out.println(e);
        	trans.rollback();
        }finally{
        	em.close();
        }
        
        getServletContext()
		.getRequestDispatcher("/getCustomerInfo")
		.forward(request, response);
        
	}
	

}
