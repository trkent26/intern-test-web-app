<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, controllers.CerealSQLConnection, entity.Cereal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>We take these rankings very CEREALsLY</title>
</head>
<body style="background-image:url('https://www.verywellfit.com/thmb/1mL7GRztKHp4uHx9EM-TK7vStUc=/3000x2002/filters:fill(FFDB5D,1)/cereal-and-milk-a911b706cf3f4fa3bfa857ec20cd3a80.jpg');">
	<h1>RANKINGS GALORE</h1>

	<hr>

	<form action="addcereal" method="post">
		<input type="text" name="cerealName">
		<button>Add Cereal</button>
	</form>
	</br>

	<div class="cereallist">
    <%
		ArrayList<Cereal> cereals = CerealSQLConnection.getCereals();    
		int size = cereals.size();
		for (int i = 0; i < size; i++) {
			String cName = cereals.get(i).getName();			
			int cRank = cereals.get(i).getRank();
			%>
			<div>
			
				<p style="font-size:20px"><%=cRank%>&nbsp;&nbsp;<%=cName%></p>			
				
				<%if (i != 0) { %>
					<form action="rankup" method="post" style="inline">
						<input type="hidden" name="cerealName" value="<%=cName%>">
						<input type="hidden" name="cerealRank" value="<%=cRank%>">
			            <button>Rank Up</button>
			        </form>
			    <% } 
			    
			    	if (i != size-1) { %>
			    	
			        <form action="rankdown" method="post" style="inline">
			        	<input type="hidden" name="cerealName" value="<%=cName%>">
						<input type="hidden" name="cerealRank" value="<%=cRank%>">
			            <button>Rank Down</button>
		            </form>
		            
		        <% } %>
		        
		        	<form action="deletecereal" method="post" style="inline">
			        	<input type="hidden" name="cerealName" value="<%=cName%>">
			        	<input type="hidden" name="cerealRank" value="<%=cRank%>">
			            <button>Delete</button>
		            </form>
		        
	        </div>
	        <br><br>
			<% 
		}
	%> <br>
     
    </div>

</body>
</html>