// Budget management functions
function showAddIncomeModal() {
  document.getElementById("add-income-modal").classList.remove("hidden");
  document.getElementById("add-income-modal").classList.add("flex");
  document.body.style.overflow = "hidden";
  
  // Set today's date as default
  const today = new Date().toISOString().split('T')[0];
  document.querySelector('#add-income-modal input[type="date"]').value = today;
}

function hideAddIncomeModal() {
  document.getElementById("add-income-modal").classList.add("hidden");
  document.getElementById("add-income-modal").classList.remove("flex");
  document.body.style.overflow = "";
  document.getElementById("add-income-form").reset();
}

function showAddExpenseModal() {
  document.getElementById("add-expense-modal").classList.remove("hidden");
  document.getElementById("add-expense-modal").classList.add("flex");
  document.body.style.overflow = "hidden";
  
  // Set today's date as default
  const today = new Date().toISOString().split('T')[0];
  document.querySelector('#add-expense-modal input[type="date"]').value = today;
}

function hideAddExpenseModal() {
  document.getElementById("add-expense-modal").classList.add("hidden");
  document.getElementById("add-expense-modal").classList.remove("flex");
  document.body.style.overflow = "";
  document.getElementById("add-expense-form").reset();
}

function addIncome() {
  alert("Thêm khoản thu thành công!");
  hideAddIncomeModal();
  location.reload();
}

function addExpense() {
  alert("Thêm khoản chi thành công!");
  hideAddExpenseModal();
  location.reload();
}

function viewDayDetail(date) {
  alert("Xem chi tiết ngày: " + date);
  // Add your view detail logic here
}

function editDayBudget(date) {
  alert("Chỉnh sửa ngân sách ngày: " + date);
  // Add your edit logic here
}

function exportBudgetReport() {
  alert("Xuất báo cáo ngân sách ra Excel");
  // Add your export logic here
}

// Close modals when clicking outside
document.addEventListener('DOMContentLoaded', function() {
  const incomeModal = document.getElementById('add-income-modal');
  const expenseModal = document.getElementById('add-expense-modal');
  
  if (incomeModal) {
    incomeModal.addEventListener('click', function(e) {
      if (e.target === this) {
        hideAddIncomeModal();
      }
    });
  }
  
  if (expenseModal) {
    expenseModal.addEventListener('click', function(e) {
      if (e.target === this) {
        hideAddExpenseModal();
      }
    });
  }
});

// Format number with thousands separator
function formatCurrency(amount) {
  return new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
  }).format(amount);
}

// Calculate profit/loss
function calculateProfit(income, expense) {
  return income - expense;
}