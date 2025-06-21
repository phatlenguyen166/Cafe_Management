// Device management functions
function showAddDeviceModal() {
    document.getElementById("add-device-modal").classList.remove("hidden");
    document.getElementById("add-device-modal").classList.add("flex");
    document.body.style.overflow = "hidden";
}

function hideAddDeviceModal() {
    document.getElementById("add-device-modal").classList.add("hidden");
    document.getElementById("add-device-modal").classList.remove("flex");
    document.body.style.overflow = "";
}

function addDevice() {
    // Add your device creation logic here
    alert("Thêm thiết bị thành công!");
    hideAddDeviceModal();
    // Reload page or update table
}

function editDevice(id) {
    // Add your edit logic here
    alert("Chỉnh sửa thiết bị ID: " + id);
}

function deleteDevice(id) {
    if (confirm("Bạn có chắc chắn muốn xóa thiết bị này?")) {
        // Add your delete logic here
        alert("Đã xóa thiết bị ID: " + id);
    }
}

function viewDevice(id) {
    // Add your view logic here
    alert("Xem chi tiết thiết bị ID: " + id);
}

function exportDeviceList() {
    // Add export logic here
    alert("Xuất danh sách thiết bị ra Excel");
}

function printDeviceList() {
    // Add print logic here
    window.print();
}

// Close modal when clicking outside
document
    .getElementById("add-device-modal")
    .addEventListener("click", function (e) {
        if (e.target === this) {
            hideAddDeviceModal();
        }
    });

// Search functionality
document.getElementById("searchInput").addEventListener("input", function (e) {
    // Add your search logic here
    console.log("Searching for:", e.target.value);
});
