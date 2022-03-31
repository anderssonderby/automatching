package anderssonderby.bestseller.automatching.controllers;

import anderssonderby.bestseller.automatching.models.MatchingPlayersDTO;
import anderssonderby.bestseller.automatching.services.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchController.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Test
    public void searchTopPlayers_topPlayersInDatabase_expectOkResult() throws Exception {
        List<MatchingPlayersDTO> topPlayers = new ArrayList<>();
        topPlayers.add(new MatchingPlayersDTO("Awesome", "Apex Legends", "PRO", 100, "EMEA"));
        topPlayers.add(new MatchingPlayersDTO("Awesome", "Among Us", "PRO", 100, "EMEA"));
        topPlayers.add(new MatchingPlayersDTO("CoolNickname", "Counter-Strike: Global Offensive", "NOOB", 50, "EMEA"));
        topPlayers.add(new MatchingPlayersDTO("CoolNickname", "Apex Legends", "PRO", 50, "EMEA"));

        given(searchService.getTopPlayers()).willReturn(topPlayers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/search/top")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"nickname\":\"Awesome\",\"gameTitle\":\"Apex Legends\",\"skillLevel\":\"PRO\",\"region\":\"EMEA\",\"credits\":100},{\"nickname\":\"Awesome\",\"gameTitle\":\"Among Us\",\"skillLevel\":\"PRO\",\"region\":\"EMEA\",\"credits\":100},{\"nickname\":\"CoolNickname\",\"gameTitle\":\"Counter-Strike: Global Offensive\",\"skillLevel\":\"NOOB\",\"region\":\"EMEA\",\"credits\":50},{\"nickname\":\"CoolNickname\",\"gameTitle\":\"Apex Legends\",\"skillLevel\":\"PRO\",\"region\":\"EMEA\",\"credits\":50}]";

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(expected, result.getResponse().getContentAsString());
    }
}
