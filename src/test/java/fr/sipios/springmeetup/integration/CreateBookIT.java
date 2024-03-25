package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:db/clear_books.sql")
public class CreateBookIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_create_book() throws Exception {
    mockMvc.perform(post("/books").contentType("application/json")
            .content("{\"title\":\"title\",\"author\":\"author\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.title").value("title"))
        .andExpect(jsonPath("$.author").value("author"));

    mockMvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.books").isArray())
        .andExpect(jsonPath("$.books[0].title").value("title"))
        .andExpect(jsonPath("$.books[0].author").value("author"));
  }
}
