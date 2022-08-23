package ru.trilonka.pastebox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.trilonka.pastebox.api.response.PasteboxResponse;
import ru.trilonka.pastebox.model.PasteboxEntity;
import ru.trilonka.pastebox.repository.PasteboxRepository;
import ru.trilonka.pastebox.service.PasteboxService;
import ru.trilonka.pastebox.util.NotFoundEntityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteboxServiceTest {

    @Autowired
    private PasteboxService pasteboxService;

    @MockBean
    private PasteboxRepository pasteboxRepository;

    @Test
    public void notExistHash() {
        when(pasteboxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pasteboxService.getByHash("giovanni giorgio"));
    }

    @Test
    public void getExistHash() {
        PasteboxEntity pastebox = new PasteboxEntity();
        pastebox.setHash("1");
        pastebox.setData("11");
        pastebox.setPublic(true);

        when(pasteboxRepository.getByHash("1")).thenReturn(pastebox);

        PasteboxResponse expected = new PasteboxResponse("11", true);
        PasteboxResponse actual = pasteboxService.getByHash("1");

        assertEquals(expected, actual);
    }
}
