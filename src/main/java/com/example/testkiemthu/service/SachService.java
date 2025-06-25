package com.example.testkiemthu.service;

import com.example.testkiemthu.model.Sach;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Service layer encapsulating business logic for managing {@link Sach} objects.
 */
public class SachService {

    private final Map<String, Sach> store = new HashMap<>();

    /**
     * Adds a new book to the in-memory store.
     *
     * @throws IllegalArgumentException if the given "maSach" already exists.
     */
    public void addSach(Sach sach) {
        validateSach(sach);
        if (store.containsKey(sach.getMaSach())) {
            throw new IllegalArgumentException("Mã sách đã tồn tại");
        }
        store.put(sach.getMaSach(), sach);
    }

    /**
     * Edits an existing book identified by {@code maSachToEdit}. This method replaces the previous record with
     * {@code updatedSach}. If {@code updatedSach.maSach} changes, the new code must not duplicate another record.
     *
     * @param maSachToEdit existing book id to be updated
     * @param updatedSach  new book data
     * @return the updated book object stored
     * @throws NoSuchElementException   if {@code maSachToEdit} does not exist
     * @throws IllegalArgumentException if validations fail or a duplicate code arises
     */
    public Sach editSach(String maSachToEdit, Sach updatedSach) {
        if (!store.containsKey(maSachToEdit)) {
            throw new NoSuchElementException("Không tìm thấy mã sách cần sửa");
        }

        // Ensure general field validity first
        validateSach(updatedSach);

        // Check duplicate when code changes
        if (!maSachToEdit.equals(updatedSach.getMaSach()) && store.containsKey(updatedSach.getMaSach())) {
            throw new IllegalArgumentException("Mã sách bị trùng");
        }

        // Remove old if code changed
        if (!maSachToEdit.equals(updatedSach.getMaSach())) {
            store.remove(maSachToEdit);
        }

        store.put(updatedSach.getMaSach(), updatedSach);
        return updatedSach;
    }

    public Sach getById(String maSach) {
        return store.get(maSach);
    }

    /* --------------------------------------------------- */
    private void validateSach(Sach sach) {
        if (sach == null) {
            throw new IllegalArgumentException("Sách không được null");
        }
        if (sach.getMaSach() == null || sach.getMaSach().isBlank()) {
            throw new IllegalArgumentException("Mã sách không hợp lệ");
        }
        if (sach.getNamXuatBan() <= 0) {
            throw new IllegalArgumentException("Năm xuất bản phải > 0");
        }
        if (sach.getGia() < 0) {
            throw new IllegalArgumentException("Giá sách không hợp lệ");
        }
    }
} 