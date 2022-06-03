package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");

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
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
        } else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1));
                Product product = this.productRepository.findById(id);
                if (product == null) {
                    resp.getWriter().println("Product not found");
                    resp.setStatus(404);
                    return;
                }
                req.setAttribute("products", product);
                getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
            } else {
                resp.getWriter().println("Bad parameter value");
                resp.setStatus(400);
            }
        }
    }
}
