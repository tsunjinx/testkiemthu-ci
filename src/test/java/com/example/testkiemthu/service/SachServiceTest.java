package com.example.testkiemthu.service;

import com.example.testkiemthu.model.Sach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SachServiceTest {

    private SachService service;

    @BeforeEach
    void setUp() {
        service = new SachService();
        service.addSach(new Sach("B1", "Book 1", "Author", 2020, 10_000));
        service.addSach(new Sach("B2", "Book 2", "Author", 2021, 15_000));
    }

    @Test
    @DisplayName("Successfully update existing book (equivalence class: valid data)")
    void testUpdateSuccess() {
        Sach updated = new Sach("B1", "Book 1 New", "Author", 2022, 12_000);
        Sach result = service.editSach("B1", updated);
        assertThat(result.getTenSach()).isEqualTo("Book 1 New");
        assertThat(service.getById("B1")).isEqualTo(updated);
    }

    @Test
    @DisplayName("Update with duplicate maSach should fail (boundary for uniqueness rule)")
    void testDuplicateMaSach() {
        Sach duplicateCode = new Sach("B2", "Book 1 New", "Author", 2022, 12_000);
        assertThatThrownBy(() -> service.editSach("B1", duplicateCode))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Mã sách bị trùng");
    }

    @Test
    @DisplayName("Update non-existing book should throw NoSuchElementException (invalid identifier)")
    void testUpdateNonExisting() {
        Sach updated = new Sach("B3", "Book 3", "Author", 2022, 10_000);
        assertThatThrownBy(() -> service.editSach("B3", updated))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Year 0 is invalid (boundary value)")
    void testInvalidYear() {
        Sach invalidYear = new Sach("B1", "Book", "Author", 0, 10_000);
        assertThatThrownBy(() -> service.editSach("B1", invalidYear))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Năm xuất bản");
    }

    @Test
    @DisplayName("Negative price is invalid (boundary value)")
    void testNegativePrice() {
        Sach negativePrice = new Sach("B1", "Book", "Author", 2022, -1);
        assertThatThrownBy(() -> service.editSach("B1", negativePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Giá sách");
    }
} 