package ru.trilonka.pastebox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.trilonka.pastebox.api.request.PasteboxRequest;
import ru.trilonka.pastebox.api.response.PasteboxResponse;
import ru.trilonka.pastebox.api.response.PasteboxUrlResponse;
import ru.trilonka.pastebox.model.PasteboxEntity;
import ru.trilonka.pastebox.repository.PasteboxRepository;
import ru.trilonka.pastebox.util.PublicStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasteboxServiceImpl implements PasteboxService {

    @Value("${app.host}")
    private String host;

    @Value("${app.public_list_size}")
    private int publicListSize;

    private final PasteboxRepository pasteboxRepository;
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public PasteboxResponse getByHash(String hash) {
        PasteboxEntity pasteboxEntity = pasteboxRepository.getByHash(hash);

        return convertToPasteboxResponse(pasteboxEntity);
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPasteboxes() {
        List<PasteboxEntity> pasteboxEntities = pasteboxRepository.getListOfPublicAndAlive(publicListSize);

        return pasteboxEntities.stream()
                .map(this::convertToPasteboxResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {
        int hash = generateId();

        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData(request.getData());
        pasteboxEntity.setId(hash);
        pasteboxEntity.setHash(Integer.toHexString(hash));
        pasteboxEntity.setPublic(request.getStatus() == PublicStatus.PUBLIC);
        pasteboxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        pasteboxRepository.add(pasteboxEntity);
        return new PasteboxUrlResponse(host + "/" + pasteboxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }

    private PasteboxResponse convertToPasteboxResponse(PasteboxEntity pasteboxEntity) {
        return new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic());
    }
}
