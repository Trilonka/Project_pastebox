package ru.trilonka.pastebox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.trilonka.pastebox.api.request.PasteboxRequest;
import ru.trilonka.pastebox.api.response.PasteboxResponse;
import ru.trilonka.pastebox.api.response.PasteboxUrlResponse;
import ru.trilonka.pastebox.service.PasteboxServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteboxController {

    private final PasteboxServiceImpl pasteboxService;

    @GetMapping("/")
    public List<PasteboxResponse> getPublicPasteList() {
        return pasteboxService.getFirstPublicPasteboxes();
    }

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable("hash") String hash) {
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest request) {
        return pasteboxService.create(request);
    }
}

