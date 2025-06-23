// Define functions immediately - before HTML is rendered
console.log("🚀 Defining functions...");
document.addEventListener("DOMContentLoaded", function () {
  const successAlert = document.querySelector(".bg-green-50");
  const errorAlert = document.querySelector(".bg-red-50");

  if (successAlert) {
    setTimeout(() => {
      successAlert.style.transition = "opacity 0.5s ease-out";
      successAlert.style.opacity = "0";
      setTimeout(() => {
        successAlert.remove();
      }, 500);
    }, 5000);
  }

  if (errorAlert) {
    setTimeout(() => {
      errorAlert.style.transition = "opacity 0.5s ease-out";
      errorAlert.style.opacity = "0";
      setTimeout(() => {
        errorAlert.remove();
      }, 500);
    }, 8000);
  }
});
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
  const datBanBtn = document.getElementById("dat-ban-btn");
  const huyBanBtn = document.getElementById("huy-ban-btn");

  // Enable xem bàn button
  if (xemBanBtn) {
    xemBanBtn.disabled = false;
    xemBanBtn.style.opacity = "1";
    xemBanBtn.style.cursor = "pointer";
  }

  // Enable đặt bàn button only for "Rảnh" tables
  if (datBanBtn) {
    if (selectedTableInfo.status.trim() === "Rảnh") {
      datBanBtn.disabled = false;
      datBanBtn.style.opacity = "1";
      datBanBtn.style.cursor = "pointer";
    } else {
      datBanBtn.disabled = true;
      datBanBtn.style.opacity = "0.5";
      datBanBtn.style.cursor = "not-allowed";
    }
  }

  // Enable hủy bàn button only for "Đã đặt" tables
  if (huyBanBtn) {
    if (selectedTableInfo.status.trim() === "Đã đặt") {
      huyBanBtn.disabled = false;
      huyBanBtn.style.opacity = "1";
      huyBanBtn.style.cursor = "pointer";
    } else {
      huyBanBtn.disabled = true;
      huyBanBtn.style.opacity = "0.5";
      huyBanBtn.style.cursor = "not-allowed";
    }
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

// Đặt bàn click function
function datBanClick() {
  console.log("📅 Đặt bàn clicked!");
  if (!selectedTableInfo) {
    alert("Vui lòng chọn một bàn trước!");
    return;
  }

  if (selectedTableInfo.status.trim() !== "Rảnh") {
    alert("Chỉ có thể đặt bàn khi bàn đang rảnh!");
    return;
  }

  showDatBanModal(selectedTableInfo);
}

// Show đặt bàn modal function
function showDatBanModal(tableInfo) {
  console.log("📋 Showing đặt bàn modal for:", tableInfo);
  const modal = document.getElementById("dat-ban-modal");
  if (!modal) {
    alert("Lỗi: Không tìm thấy modal đặt bàn!");
    return;
  }

  try {
    // Update modal title
    const titleElement = document.getElementById("modal-dat-ban-title");
    if (titleElement) {
      titleElement.textContent = `Đặt ${tableInfo.name}`;
    }

    // Set default date to today
    const ngayInput = document.getElementById("ngay");
    if (ngayInput) {
      const today = new Date().toISOString().split("T")[0];
      ngayInput.value = today;
    }

    // Set default time to current time + 1 hour
    const gioInput = document.getElementById("gio");
    if (gioInput) {
      const now = new Date();
      now.setHours(now.getHours() + 1);
      const timeString = now.toTimeString().slice(0, 5);
      gioInput.value = timeString;
    }

    // Clear form
    document.getElementById("khach-hang").value = "";
    document.getElementById("sdt").value = "";

    // Show modal
    modal.style.display = "flex";
    document.body.style.overflow = "hidden";

    // Focus on first input
    document.getElementById("khach-hang").focus();
  } catch (error) {
    console.error("💥 Error showing đặt bàn modal:", error);
    alert("Lỗi hiển thị modal: " + error.message);
  }
}

// Hide đặt bàn modal function
function hideDatBanModal() {
  const modal = document.getElementById("dat-ban-modal");
  if (modal) {
    modal.style.display = "none";
    document.body.style.overflow = "";
  }
}

// Handle form submission
document.addEventListener("DOMContentLoaded", function () {
  const datBanForm = document.getElementById("dat-ban-form");
  if (datBanForm) {
    datBanForm.addEventListener("submit", function (e) {
      e.preventDefault();

      const khachHang = document.getElementById("khach-hang").value;
      const sdt = document.getElementById("sdt").value;
      const ngay = document.getElementById("ngay").value;
      const gio = document.getElementById("gio").value;

      if (!khachHang || !sdt || !ngay || !gio) {
        alert("Vui lòng điền đầy đủ thông tin!");
        return;
      }

      // Validate phone number (basic)
      if (!/^\d{10,11}$/.test(sdt)) {
        alert("Số điện thoại không hợp lệ!");
        return;
      }

      // Tạo datetime từ ngày và giờ
      const dateTimeString = ngay + "T" + gio + ":00.000Z";

      // Tạo form để submit
      const form = document.createElement("form");
      form.method = "POST";
      form.action = "/dat-ban";
      form.style.display = "none";

      // Thêm các field
      const fields = {
        maBan: selectedTableInfo.id,
        tenKhachHang: khachHang,
        sdtKhachHang: sdt,
        ngayGioDat: dateTimeString,
      };

      Object.keys(fields).forEach((key) => {
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = key;
        input.value = fields[key];
        form.appendChild(input);
      });

      // Thêm CSRF token nếu có
      const csrfToken = document.querySelector('meta[name="_csrf"]');
      if (csrfToken) {
        const csrfInput = document.createElement("input");
        csrfInput.type = "hidden";
        csrfInput.name = "_csrf";
        csrfInput.value = csrfToken.getAttribute("content");
        form.appendChild(csrfInput);
      }

      document.body.appendChild(form);
      form.submit();
    });
  }
});

// Hủy bàn click function
function huyBanClick() {
  console.log("❌ Hủy bàn clicked!");
  if (!selectedTableInfo) {
    alert("Vui lòng chọn một bàn trước!");
    return;
  }

  if (selectedTableInfo.status.trim() !== "Đã đặt") {
    alert("Chỉ có thể hủy bàn khi bàn đã được đặt!");
    return;
  }

  showHuyBanModal(selectedTableInfo);
}

// Show hủy bàn modal function
function showHuyBanModal(tableInfo) {
  console.log("❌ Showing hủy bàn modal for:", tableInfo);
  const modal = document.getElementById("huy-ban-modal");
  if (!modal) {
    alert("Lỗi: Không tìm thấy modal hủy bàn!");
    return;
  }

  try {
    // Update modal title
    const titleElement = document.getElementById("modal-huy-ban-title");
    if (titleElement) {
      titleElement.textContent = `Xác nhận hủy ${tableInfo.name}`;
    }

    // Update table name
    const tableNameElement = document.getElementById("huy-ban-table-name");
    if (tableNameElement) {
      tableNameElement.textContent = tableInfo.name + "?";
    }

    // Show customer info (mock data - you can replace with real data)
    const customerInfoElement = document.getElementById(
      "huy-ban-customer-info"
    );
    if (customerInfoElement) {
      customerInfoElement.innerHTML = `
        <div style="margin-bottom: 4px">👤 Khách hàng: <strong>Nguyễn Văn A</strong></div>
        <div style="margin-bottom: 4px">📞 SĐT: <strong>0123456789</strong></div>
        <div>🕐 Thời gian đặt: <strong>14:00 - 25/12/2024</strong></div>
      `;
    }

    // Show modal
    modal.style.display = "flex";
    document.body.style.overflow = "hidden";
  } catch (error) {
    console.error("💥 Error showing hủy bàn modal:", error);
    alert("Lỗi hiển thị modal: " + error.message);
  }
}

// Hide hủy bàn modal function
function hideHuyBanModal() {
  const modal = document.getElementById("huy-ban-modal");
  if (modal) {
    modal.style.display = "none";
    document.body.style.overflow = "";
  }
}

// Confirm hủy bàn function
function confirmHuyBan() {
  if (!selectedTableInfo) {
    alert("Lỗi: Không tìm thấy thông tin bàn!");
    return;
  }

  console.log("🔄 Confirming hủy bàn for table ID:", selectedTableInfo.id);

  // Tạo form để submit
  const form = document.createElement("form");
  form.method = "POST";
  form.action = `/huy-dat-ban/${selectedTableInfo.id}`;
  form.style.display = "none";

  // Thêm CSRF token nếu có
  const csrfToken = document.querySelector('meta[name="_csrf"]');
  if (csrfToken) {
    const csrfInput = document.createElement("input");
    csrfInput.type = "hidden";
    csrfInput.name = "_csrf";
    csrfInput.value = csrfToken.getAttribute("content");
    form.appendChild(csrfInput);
  }

  document.body.appendChild(form);
  form.submit();
}

console.log("✅ All functions defined including hủy bàn!");
