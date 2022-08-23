package ru.trilonka.pastebox.repository;

import org.springframework.stereotype.Repository;
import ru.trilonka.pastebox.model.PasteboxEntity;
import ru.trilonka.pastebox.util.NotFoundEntityException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PasteboxRepositoryMap implements PasteboxRepository {

    private final Map<String, PasteboxEntity> vault = new ConcurrentHashMap<>();

    @Override
    public PasteboxEntity getByHash(String hash) {
        PasteboxEntity pastebox = vault.get(hash);

        if (pastebox == null) {
            throw new NotFoundEntityException("Pastebox not found with hash=" + hash);
        }

        return vault.get(hash);
    }

    @Override
    public List<PasteboxEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime currentTime = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteboxEntity::isPublic)
                .filter(pasteboxEntity -> pasteboxEntity.getLifetime().isAfter(currentTime))
                .sorted(Comparator.comparing(PasteboxEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteboxEntity pastebox) {
        vault.put(pastebox.getHash(), pastebox);
    }
}
