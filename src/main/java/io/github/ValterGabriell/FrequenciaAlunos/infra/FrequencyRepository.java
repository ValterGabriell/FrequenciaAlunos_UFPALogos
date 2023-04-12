package io.github.ValterGabriell.FrequenciaAlunos.infra;

import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyRepository extends JpaRepository<Frequency, String> {
}
