// Define functions immediately - before HTML is rendered
console.log("🚀 Defining functions...");

// Global variables
let selectedTable = null;
let selectedTableInfo = null;

// Select table function
function selectTable(element) {
    console.log("🖱️ Table clicked:", element.getAttribute("data-table-name"));

    // Remove previous selection
    if (selectedTable) {
        selectedTable.style.boxShadow = "";
        const prevIndicator = selectedTable.querySelector(".selected-indicator");
        if (prevIndicator) {
            prevIndicator.style.display = "none";
        }
    }

    // Add selection to clicked table
    element.style.boxShadow = "0 0 0 4px rgba(147, 51, 234, 0.5)";
    const indicator = element.querySelector(".selected-indicator");
    if (indicator) {
        indicator.style.display = "block";
    }

    selectedTable = element;

    // Store selected table info
    selectedTableInfo = {
        id: element.getAttribute("data-table-id"),
        name: element.getAttribute("data-table-name"),
        status: element.getAttribute("data-table-status"),
    };

    console.log("✅ Selected table info:", selectedTableInfo);

    // Update button states
    const xemBanBtn = document.getElementById("xem-ban-btn");
    if (xemBanBtn) {
        xemBanBtn.disabled = false;
        xemBanBtn.style.opacity = "1";
        xemBanBtn.style.cursor = "pointer";
    }
}

// Xem ban click function
function xemBanClick() {
    console.log("👁️ Xem bàn clicked!");
    if (selectedTableInfo) {
        showTableInfoModal(selectedTableInfo);
    } else {
        alert("Vui lòng chọn một bàn trước!");
    }
}

// Show modal function
function showTableInfoModal(tableInfo) {
    console.log("🎭 Showing modal for:", tableInfo);
    const modal = document.getElementById("table-info-modal");
    if (!modal) {
        alert("Lỗi: Không tìm thấy modal!");
        return;
    }

    try {
        // Update modal title
        const titleElement = document.getElementById("modal-table-title");
        if (titleElement) {
            titleElement.textContent = `Xem thông tin ${tableInfo.name}`;
        }

        // Update table status
        const statusElement = document.getElementById("modal-table-status");
        if (statusElement) {
            statusElement.textContent = tableInfo.status;
            // Update status styling
            if (tableInfo.status === "Rảnh") {
                statusElement.style.background = "#dcfce7";
                statusElement.style.color = "#166534";
            } else if (tableInfo.status === "Đang sử dụng") {
                statusElement.style.background = "#fecaca";
                statusElement.style.color = "#991b1b";
            } else if (tableInfo.status === "Đã đặt") {
                statusElement.style.background = "#fef3c7";
                statusElement.style.color = "#92400e";
            }
        }

        // Update modal content based on status
        updateModalContent(tableInfo);

        // Show modal
        modal.style.display = "flex";
        document.body.style.overflow = "hidden";
    } catch (error) {
        console.error("💥 Error showing modal:", error);
        alert("Lỗi hiển thị modal: " + error.message);
    }
}

// Update modal content function
function updateModalContent(tableInfo) {
    const orderItemsContainer = document.getElementById("modal-order-items");
    const reservationInfo = document.getElementById("reservation-info");
    const totalAmount = document.getElementById("total-amount");
    const modalTime = document.getElementById("modal-table-time");
    const modalActionBtn = document.getElementById("modal-action-btn");

    if (tableInfo.status === "Rảnh") {
        if (orderItemsContainer) {
            orderItemsContainer.innerHTML = `
          <div style="padding: 32px 16px; text-align: center; color: #6b7280;">
            <div style="font-size: 2rem; margin-bottom: 8px;">☕</div>
            <p>Bàn hiện tại trống</p>
          </div>
        `;
        }
        if (reservationInfo) reservationInfo.style.display = "none";
        if (totalAmount) totalAmount.style.display = "none";
        if (modalTime) modalTime.textContent = "Trống";
        if (modalActionBtn) modalActionBtn.innerHTML = "➕ Đặt bàn";
    } else if (tableInfo.status === "Đang sử dụng") {
        if (orderItemsContainer) {
            orderItemsContainer.innerHTML = `
          <div style="padding: 8px 16px; display: flex; justify-content: space-between; border-bottom: 1px solid #e5e7eb;">
            <span>Sữa chua</span>
            <span>2</span>
          </div>
          <div style="padding: 8px 16px; display: flex; justify-content: space-between; border-bottom: 1px solid #e5e7eb;">
            <span>Cà phê sữa</span>
            <span>3</span>
          </div>
          <div style="padding: 8px 16px; display: flex; justify-content: space-between;">
            <span>Có ca có la</span>
            <span>1</span>
          </div>
        `;
        }
        if (reservationInfo) reservationInfo.style.display = "none";
        if (totalAmount) {
            totalAmount.style.display = "block";
            const totalAmountSpan = document.getElementById("modal-total-amount");
            if (totalAmountSpan) totalAmountSpan.textContent = "285,000₫";
        }
        if (modalTime) modalTime.textContent = "Bắt đầu: 10h30 - 25/12/2024";
        if (modalActionBtn) modalActionBtn.innerHTML = "✏️ Chỉnh sửa";
    } else if (tableInfo.status === "Đã đặt") {
        if (orderItemsContainer) {
            orderItemsContainer.innerHTML = `
          <div style="padding: 32px 16px; text-align: center; color: #6b7280;">
            <div style="font-size: 2rem; margin-bottom: 8px;">🕐</div>
            <p>Chưa có món nào được gọi</p>
          </div>
        `;
        }
        if (reservationInfo) {
            reservationInfo.style.display = "block";
            const reservationInfoSpan = document.getElementById(
                "modal-reservation-info"
            );
            if (reservationInfoSpan)
                reservationInfoSpan.textContent = "Lê Na, 14h00 - 25/12/2024";
        }
        if (totalAmount) totalAmount.style.display = "none";
        if (modalTime) modalTime.textContent = "Đặt trước: 14h00 - 25/12/2024";
        if (modalActionBtn) modalActionBtn.innerHTML = "👥 Bắt đầu phục vụ";
    }
}

// Hide modal function
function hideModal() {
    const modal = document.getElementById("table-info-modal");
    if (modal) {
        modal.style.display = "none";
        document.body.style.overflow = "";
    }
}

console.log("✅ All functions defined!");
