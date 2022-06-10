package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

public class NoteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // attribute string for web application title
        String applicationTitle = "Simple Note Keeper";
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        // to read files
        BufferedReader pathreader = new BufferedReader(new FileReader(new File(path)));
        String title = pathreader.readLine();
        String content = pathreader.readLine();
        
        // added read items to the object of Note class
        Note openNote = new Note(title, content);
        
        // closing the BufferedReader
        pathreader.close();
        
        // setting parameters as attributes
        request.setAttribute("notehead", applicationTitle);
        request.setAttribute("note", openNote);
        
        // check if user clicked on edit link
        if(request.getParameter("edit") != null){
            // loading the JSP
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            // stop the code call
            return;
        }
        
        // loading the JSP
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        // stop the code call
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // attribute string for web application title
        String applicationTitle = "Simple Note Keeper";
        request.setAttribute("notehead", applicationTitle);
        
        // getting the content from parameters
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        
        // added edited items to the object of Note class
        Note openNote = new Note(title, content);
        // setting Note object as a attribute
        request.setAttribute("note", openNote);
        
        // to write to a file
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        // writing into buffer memory
        out.println(title);
        out.println(content);
        
        // closing PrintWriter
        out.close();
        
        // loading the JSP
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        // stop the code call
        return;
    }
}
