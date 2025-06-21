function showImportModal() {
    // Set ngày hiện tại
    document.getElementById('import-date').value = new Date().toISOString().split('T')[0];

    document.getElementById('import-stock-modal').classList.remove('hidden');
    document.getElementById('import-stock-modal').classList.add('flex');
    document.body.style.overflow = 'hidden';
}

function hideImportModal() {
    document.getElementById('import-stock-modal').classList.add('hidden');
    document.getElementById('import-stock-modal').classList.remove('flex');
    document.body.style.overflow = '';

    // Reset form
    document.getElementById('import-stock-form').reset();
    document.getElementById('import-total').value = '0₫';
    document.getElementById('custom-product-name').style.display = 'none';
}

// THÊM FUNCTION MỚI CHO IMPORT
function loadImportProductInfo() {
    const selectElement = document.getElementById('import-product-select');
    const customNameDiv = document.getElementById('custom-product-name');
    const customNameInput = document.getElementById('import-custom-name');

    if (selectElement.value === 'khac') {
        // Hiển thị input tùy chỉnh
        customNameDiv.style.display = 'block';
        customNameInput.required = true;
    } else {
        // Ẩn input tùy chỉnh
        customNameDiv.style.display = 'none';
        customNameInput.required = false;
        customNameInput.value = '';
    }
}

function calculateImportTotal() {
    const quantity = parseFloat(document.getElementById('import-quantity').value) || 0;
    const price = parseFloat(document.getElementById('import-price').value) || 0;
    const total = quantity * price;

    document.getElementById('import-total').value = total.toLocaleString('vi-VN') + '₫';
}

function submitImportForm() {
    const form = document.getElementById('import-stock-form');

    // Validate form
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    // Get product name
    const selectElement = document.getElementById('import-product-select');
    let productName = '';

    if (selectElement.value === 'khac') {
        productName = document.getElementById('import-custom-name').value;
    } else {
        productName = selectElement.options[selectElement.selectedIndex].text;
    }

    // Get form data
    const formData = {
        productValue: selectElement.value,
        productName: productName,
        quantity: document.getElementById('import-quantity').value,
        unit: document.getElementById('import-unit').value,
        price: document.getElementById('import-price').value,
        date: document.getElementById('import-date').value,
        note: document.getElementById('import-note').value,
        total: document.getElementById('import-total').value
    };

    console.log('Import Data:', formData);

    // TODO: Send to server
    alert('Nhập kho thành công!\n' +
        'Sản phẩm: ' + formData.productName + '\n' +
        'Số lượng: ' + formData.quantity + ' ' + formData.unit + '\n' +
        'Ngày nhập: ' + formData.date + '\n' +
        'Tổng tiền: ' + formData.total);

    hideImportModal();
    // Reload page to update table
    location.reload();
}

// Export Stock Functions - CẬP NHẬT
function showExportModal() {
    // Set ngày hiện tại
    document.getElementById('export-date').value = new Date().toISOString().split('T')[0];

    document.getElementById('export-stock-modal').classList.remove('hidden');
    document.getElementById('export-stock-modal').classList.add('flex');
    document.body.style.overflow = 'hidden';
}

function hideExportModal() {
    document.getElementById('export-stock-modal').classList.add('hidden');
    document.getElementById('export-stock-modal').classList.remove('flex');
    document.body.style.overflow = '';

    // Reset form
    document.getElementById('export-stock-form').reset();
    document.getElementById('export-total').value = '0₫';
    document.getElementById('export-product-info').style.display = 'none';
    document.getElementById('export-unit-display').textContent = '( đơn vị )';
}

// CẬP NHẬT FUNCTION CHO EXPORT
function loadExportProductInfo() {
    const selectElement = document.getElementById('export-product-select');
    const selectedOption = selectElement.options[selectElement.selectedIndex];

    if (selectedOption.value) {
        // Get data from option attributes
        const unit = selectedOption.getAttribute('data-unit');
        const stock = selectedOption.getAttribute('data-stock');
        const price = selectedOption.getAttribute('data-price');

        // Update display
        document.getElementById('export-current-stock').textContent = stock + ' ' + unit;
        document.getElementById('export-product-unit').textContent = unit;
        document.getElementById('export-product-price').textContent = parseInt(price).toLocaleString('vi-VN') + '₫';
        document.getElementById('export-unit-display').textContent = '( ' + unit + ' )';

        // Set max quantity và validation
        const quantityInput = document.getElementById('export-quantity');
        quantityInput.max = stock;

        // Update stock warning
        document.getElementById('stock-warning').textContent =
            `* Số lượng không được vượt quá tồn kho (${stock} ${unit})`;

        // Show info panel
        document.getElementById('export-product-info').style.display = 'block';
    } else {
        document.getElementById('export-product-info').style.display = 'none';
        document.getElementById('export-unit-display').textContent = '( đơn vị )';
        document.getElementById('stock-warning').textContent = '* Số lượng không được vượt quá tồn kho';
    }
}

function calculateExportTotal() {
    const quantity = parseFloat(document.getElementById('export-quantity').value) || 0;
    const selectElement = document.getElementById('export-product-select');
    const selectedOption = selectElement.options[selectElement.selectedIndex];

    if (selectedOption.value) {
        const price = parseFloat(selectedOption.getAttribute('data-price')) || 0;
        const maxStock = parseFloat(selectedOption.getAttribute('data-stock')) || 0;
        const unit = selectedOption.getAttribute('data-unit');

        // Check if quantity exceeds stock
        if (quantity > maxStock) {
            alert(`Số lượng xuất không được vượt quá tồn kho (${maxStock} ${unit})!`);
            document.getElementById('export-quantity').value = maxStock;
            return;
        }

        const total = quantity * price;
        document.getElementById('export-total').value = total.toLocaleString('vi-VN') + '₫';
    }
}

function submitExportForm() {
    const form = document.getElementById('export-stock-form');

    // Validate form
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    // Get form data
    const selectElement = document.getElementById('export-product-select');
    const selectedOption = selectElement.options[selectElement.selectedIndex];
    const reasonSelect = document.getElementById('export-reason');
    const reasonText = reasonSelect.options[reasonSelect.selectedIndex].text;

    const formData = {
        productId: selectedOption.value,
        productName: selectedOption.text,
        quantity: document.getElementById('export-quantity').value,
        unit: selectedOption.getAttribute('data-unit'),
        date: document.getElementById('export-date').value,
        reason: document.getElementById('export-reason').value,
        reasonText: reasonText,
        note: document.getElementById('export-note').value,
        total: document.getElementById('export-total').value
    };

    console.log('Export Data:', formData);

    // TODO: Send to server
    alert('Xuất kho thành công!\n' +
        'Sản phẩm: ' + formData.productName + '\n' +
        'Số lượng: ' + formData.quantity + ' ' + formData.unit + '\n' +
        'Ngày xuất: ' + formData.date + '\n' +
        'Lý do: ' + formData.reasonText + '\n' +
        'Giá trị: ' + formData.total);

    hideExportModal();
    // Reload page to update table
    location.reload();
}

// Close modals when clicking outside
document.getElementById('import-stock-modal').addEventListener('click', function(e) {
    if (e.target === this) {
        hideImportModal();
    }
});

document.getElementById('export-stock-modal').addEventListener('click', function(e) {
    if (e.target === this) {
        hideExportModal();
    }
});

// Existing functions... (giữ nguyên các function cũ)
function showAddProductModal() {
    document.getElementById("add-product-modal").classList.remove("hidden");
    document.getElementById("add-product-modal").classList.add("flex");
    document.body.style.overflow = "hidden";
}

function hideAddProductModal() {
    document.getElementById("add-product-modal").classList.add("hidden");
    document.getElementById("add-product-modal").classList.remove("flex");
    document.body.style.overflow = "";
    document.getElementById("add-product-form").reset();
    document.getElementById("total-amount").value = "0₫";
}

function calculateTotal() {
    const quantity = parseFloat(document.querySelector('input[type="number"][placeholder="0"]').value) || 0;
    const price = parseFloat(document.querySelector('input[type="number"][placeholder="0"]:last-of-type').value) || 0;
    const total = quantity * price;
    document.getElementById("total-amount").value = total.toLocaleString("vi-VN") + "₫";
}

function addProduct() {
    alert("Thêm hàng hóa thành công!");
    hideAddProductModal();
    location.reload();
}

function editProduct(id) {
    alert("Chỉnh sửa hàng hóa ID: " + id);
}

function deleteProduct(id) {
    if (confirm("Bạn có chắc chắn muốn xóa hàng hóa này?")) {
        alert("Đã xóa hàng hóa ID: " + id);
        location.reload();
    }
}

function viewProduct(id) {
    alert("Xem chi tiết hàng hóa ID: " + id);
}

function exportInventoryReport() {
    alert("Xuất báo cáo kho ra Excel");
}

document.getElementById("add-product-modal").addEventListener("click", function (e) {
    if (e.target === this) {
        hideAddProductModal();
    }
});
