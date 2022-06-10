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
        
        String applicationTitle = "Simple Note Keeper";
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        // to read files
        BufferedReader pathreader = new BufferedReader(new FileReader(new File(path)));
        String title = pathreader.readLine();
        String content = pathreader.readLine();
        Note openNote = new Note(title, content);
        pathreader.close();
        
        
        request.setAttribute("notehead", applicationTitle);
        request.setAttribute("note", openNote);
        
        if(request.getParameter("edit") != null){
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String applicationTitle = "Simple Note Keeper";
        request.setAttribute("notehead", applicationTitle);
        
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        Note openNote = new Note(title, content);
        
        request.setAttribute("note", openNote);
        
        // to write to a file
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        out.println(title);
        out.println(content);
        out.close();
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        return;
    }
}
