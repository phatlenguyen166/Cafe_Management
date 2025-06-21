// Menu management functions
function showAddMenuItemModal() {
    document.getElementById("add-menu-item-modal").classList.remove("hidden");
    document.getElementById("add-menu-item-modal").classList.add("flex");
    document.body.style.overflow = "hidden";
}

function hideAddMenuItemModal() {
    document.getElementById("add-menu-item-modal").classList.add("hidden");
    document.getElementById("add-menu-item-modal").classList.remove("flex");
    document.body.style.overflow = "";
    document.getElementById("add-menu-item-form").reset();
}

function addMenuItem() {
    alert("Thêm món mới thành công!");
    hideAddMenuItemModal();
    location.reload();
}

function editMenuItem(id) {
    alert("Chỉnh sửa món ăn ID: " + id);
}

function deleteMenuItem(id) {
    if (confirm("Bạn có chắc chắn muốn xóa món ăn này?")) {
        alert("Đã xóa món ăn ID: " + id);
        location.reload();
    }
}

function toggleMenuItemStatus(id) {
    if (confirm("Bạn có muốn thay đổi trạng thái của món ăn này?")) {
        alert("Đã thay đổi trạng thái món ăn ID: " + id);
        location.reload();
    }
}

function publishMenuItem(id) {
    if (confirm("Bạn có muốn xuất bản món ăn này?")) {
        alert("Đã xuất bản món ăn ID: " + id);
        location.reload();
    }
}

function showCategoryModal() {
    alert("Chức năng quản lý danh mục đang được phát triển!");
}

function bulkUpdatePrices() {
    alert("Chức năng cập nhật giá hàng loạt đang được phát triển!");
}

function exportMenu() {
    alert("Xuất thực đơn ra file PDF/Excel");
}

// Close modal when clicking outside
document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('add-menu-item-modal');
    if (modal) {
        modal.addEventListener('click', function(e) {
            if (e.target === this) {
                hideAddMenuItemModal();
            }
        });
    }
});