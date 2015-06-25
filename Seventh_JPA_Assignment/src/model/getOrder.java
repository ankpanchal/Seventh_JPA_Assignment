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
 * Servlet implementation class getOrder
 */
@WebServlet("/getOrder")
public class getOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrder() {
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
		String customerId = request.getParameter("customerId");
        Customer cust = selectCustomer(customerId);
        List<Order> orders = cust.getOrders();
      Iterator<Order> iterator = orders.iterator();
        
        String tableInfo = "";
        
     
        
        tableInfo += "<CENTER>";
        tableInfo += "<TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=70%>";
        
        	tableInfo += cust.getFirstName()+" "+cust.getLastName()+"\n";
        	tableInfo += cust.getEmailAddress()+"\n";
            
            
        
        
        //First Row
	        tableInfo += "<tr>";
	        tableInfo += "<td <CENTER>>" +"ORDER ID" + "</CENTER></td>";
	        tableInfo += "<td <CENTER>>" +"ORDER DATE" + "</CENTER></td>";
	        tableInfo += "<td <CENTER>>" +"SHIP AMOUNT" + "</CENTER></td>";
	        tableInfo += "</tr>";
	           
            
	        
	        //Second Row
            while(iterator.hasNext())
            {
            Order ord = (Order)iterator.next();
            tableInfo += "<tr>";
            tableInfo += "<td>"+ord.getOrderId()+"</td>";
            tableInfo += "<td>"+ord.getOrderDate()+"</td>";
            tableInfo += "<td>"+ord.getShipAmount()+"</td>";
            tableInfo += "</tr>";
            }
        
            tableInfo += "</table>";
            tableInfo += "</CENTER>";
            
            request.setAttribute("tableInfo", tableInfo);
         
            
            getServletContext()
			.getRequestDispatcher("/getOrder.jsp")
			.forward(request, response);
      
            
}
	public static Customer selectCustomer(String custID){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c from Customer c"
				+ " WHERE c.customerId = :custID";
		
		TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
		q.setParameter("custID", Long.parseLong(custID));
		model.Customer cust = null;
		try
		{
			cust = q.getSingleResult();
			
			
		} finally{
			em.close();
		}
		return cust;
	}


}
