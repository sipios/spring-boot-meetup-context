package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:db/delete_book.sql")
public class DeleteOneBookIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_delete_book() throws Exception {
    mockMvc.perform(delete("/books/203"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/books/203"))
        .andExpect(status().isNotFound());
  }
}
