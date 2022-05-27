package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        productRepository.insert(new Product("Apple", 10));
        productRepository.insert(new Product("Pear", 15));
        productRepository.insert(new Product("Egg", 25));
        productRepository.insert(new Product("Watermelon", 4200000));
        productRepository.insert(new Product("Melon", 74));
        productRepository.insert(new Product("Banana", 102));
        productRepository.insert(new Product("Potato", 1501));
        productRepository.insert(new Product("Tomato", 3));
        productRepository.insert(new Product("Cucumber", 98));
        productRepository.insert(new Product("Pepper", 140));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Name</th>");
        wr.println("<th>Cost</th>");
        wr.println("</tr>");

        if(req.getPathInfo() == null) {
            for (Product product: productRepository.findAll()
            ) {
                wr.println("<tr>");
                wr.println("<th>"+product.getId()+"</th>");
                wr.println("<th>"+product.getTitle()+"</th>");
                wr.println("<th>"+product.getCost()+"</th>");
                wr.println("</tr>");
            }
        } else {
            try {
                int id = Integer.parseInt(req.getPathInfo().split("/")[1]);
                wr.println("<tr>");
                wr.println("<th>"+productRepository.findById(id).getId()+"</th>");
                wr.println("<th>"+productRepository.findById(id).getTitle()+"</th>");
                wr.println("<th>"+productRepository.findById(id).getCost()+"</th>");
                wr.println("</tr>");
            } catch (NumberFormatException e) {

            }
        }
        wr.println("</table>");

    }
}
