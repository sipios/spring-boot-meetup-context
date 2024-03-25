package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:db/insert_book.sql")
public class GetOneBookIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_retrieve_book() throws Exception {
    mockMvc.perform(get("/books/202"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(202L))
        .andExpect(jsonPath("$.title").value("Java"))
        .andExpect(jsonPath("$.author").value("John"));
  }
}
