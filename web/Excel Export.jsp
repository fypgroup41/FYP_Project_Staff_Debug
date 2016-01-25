<%
response.setContentType("text/csv");
response.setHeader("Content-Disposition", "attachment; filename=mult_table.csv");
                                                                                    
for(int i = 1; i < 12; i++){
    for(int j = 1; j < 12; j++){
        if(j != 1) out.print(",");
        out.print(String.valueOf(i * j));
    }
    out.println();
}
%>