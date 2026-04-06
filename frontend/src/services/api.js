import { auth } from '../firebase';

const BASE_URL = 'http://localhost:8080/api';

const fetchWithToken = async (url, options = {}) => {
  try {
    const token = await auth.currentUser?.getIdToken();
    const headers = {
      'Content-Type': 'application/json',
      ...options.headers,
    };

    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }

    const response = await fetch(url, { ...options, headers });
    
    // We expect the backend to return ApiResponse<T>
    const result = await response.json();
    
    if (!response.ok) {
      throw new Error(result.message || 'API call failed');
    }
    
    return result.data; // Return the 'data' field directly for convenience
  } catch (error) {
    console.error('API Context Error:', error);
    throw error;
  }
};

export const apiService = {
  // User APIs
  createUserProfile: async (userData) => {
    return fetchWithToken(`${BASE_URL}/users`, {
      method: 'POST',
      body: JSON.stringify(userData)
    });
  },

  getCurrentUser: async () => {
    return fetchWithToken(`${BASE_URL}/users/me`);
  },

  // Doctor APIs
  registerDoctor: async (doctorData) => {
    return fetchWithToken(`${BASE_URL}/doctors`, {
      method: 'POST',
      body: JSON.stringify(doctorData)
    });
  },

  getDoctors: async (specialization = '') => {
    const url = specialization 
      ? `${BASE_URL}/doctors?specialization=${specialization}` 
      : `${BASE_URL}/doctors`;
    return fetchWithToken(url);
  },
  
  getDoctorById: async (id) => {
    return fetchWithToken(`${BASE_URL}/doctors/${id}`);
  },

  getDoctorAvailability: async (id) => {
    return fetchWithToken(`${BASE_URL}/doctors/${id}/availability`);
  },

  // Appointment APIs
  createAppointment: async (appointmentData) => {
    return fetchWithToken(`${BASE_URL}/appointments`, {
      method: 'POST',
      body: JSON.stringify(appointmentData)
    });
  },

  getMyAppointments: async () => {
    return fetchWithToken(`${BASE_URL}/appointments`);
  },

  updateAppointmentStatus: async (id, statusData) => {
    return fetchWithToken(`${BASE_URL}/appointments/${id}/status`, {
      method: 'PUT',
      body: JSON.stringify(statusData)
    });
  },

  // Medical Records APIs
  getPatientRecords: async () => {
    return fetchWithToken(`${BASE_URL}/records`);
  },

  addMedicalRecord: async (recordData) => {
    return fetchWithToken(`${BASE_URL}/records`, {
      method: 'POST',
      body: JSON.stringify(recordData)
    });
  }
};
