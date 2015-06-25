package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class getCustomerInfo
 */
@WebServlet("/getCustomerInfo")
public class getCustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCustomerInfo() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        List<Customer> customers = selectAllCustomers();
      Iterator<Customer> iterator = customers.iterator();
        
        String tableInfo = "";
        
     
        
        tableInfo += "<CENTER>";
        tableInfo += "<TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=70%>";
        
            
            
            //First Row
	        tableInfo += "<tr>";
	        tableInfo += "<td <CENTER>>" +"CUSTOMER ID" + "</CENTER></td>";
	        tableInfo += "<td <CENTER>>" +"NAME" + "</CENTER></td>";
	        tableInfo += "<td <CENTER>>" +"EMAIL ADDRESS" + "</CENTER></td>";
	        tableInfo += "</tr>";
	           
            //Second Row
            while(iterator.hasNext())
            {
            Customer cust = (Customer)iterator.next();
            tableInfo += "<tr>";
            tableInfo += "<td>"+cust.getCustomerId()+"</td>";
            tableInfo += "<td><a href = 'getOrder?customerId=" +cust.getCustomerId()+"'>" +cust.getFirstName()+" " +cust.getLastName()+"</td>";
            tableInfo += "<td>"+cust.getEmailAddress()+"</td>";
            tableInfo += "</tr>";
            }
        
            tableInfo += "</table>";
            tableInfo += "</CENTER>";
            
            request.setAttribute("tableInfo", tableInfo);
         
            
            getServletContext()
			.getRequestDispatcher("/getCustomerInfo.jsp")
			.forward(request, response);
      
            
}
	public static List<Customer> selectAllCustomers(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c from Customer c";
		
		TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
		
		List<Customer> customers;
		try
		{
			customers = q.getResultList();
			if (customers == null || customers.isEmpty())
				customers = null;	
			
		} finally{
			em.close();
		}
		return customers;
	}

}