//package ignou.aciil.ips;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class DownloadFile {
//    private String token=null, path=null;
//
//    public DownloadFile()
//    {
//
//    }
//
//    public DownloadFile(String token, String path)
//    {
//        this.token=token;
//        this.path=path;
//    }
//
//    public void setToken(String token)
//    {
//        this.token=token;
//
//    }
//
//    public void setPath(String path)
//    {
//      
//        this.path=path;
//    }
//
//    public void getConnection()
//    {
//        DataSource ds = null;
//              try {
//               ds = (DataSource)new InitialContext().lookup("jdbc/ipsDB");
//              }
//              catch(NamingException e)
//              { }
//
//        try {
//
//             Connection con=ds.getConnection();
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select BINARYFILE, FILENAME from uploads where UPLOADID='"+token+"'");
//            byte[]bg=null;
//            String filename=null;
//            while(rs.next())
//            {
//                filename=rs.getString("FILENAME");
//                bg=rs.getBytes("BINARYFILE");
//
//            }
//
//            File file = new File(path+"/"+filename);
//
//            FileOutputStream fos =new FileOutputStream(file);
//            for (int i = 0; i <bg.length; i++) {
//
//                fos.write(bg[i]);
//            }
//            con.close();
//
//        } catch (Exception e) {
//        }
//    }
//
//}
