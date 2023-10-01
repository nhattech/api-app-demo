import React, { useEffect, useState } from 'react';

import './App.css';

function App() {
  const [customers, setCustomers] = useState([]);
  const [email, setEmail] = useState('');
  const [uuid, setUuid] = useState('');
  const [password, setPassword] = useState('');
  const [isEdit, setIsEdit] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const addContact = () => {
    saveDatabase();
    setIsEdit(false);
  };

  const editContact = (uuid) => {
    const contact = customers.find((c) => c.uuid === uuid);
    setIsEdit(true);
    setEmail(contact.email);
    setPassword(contact.password);
    setUuid(uuid);
  };

  const deleteContact = (id) => {
    deleteDatabase(id);
  };

  const deleteDatabase = (id) => {
    console.log(id);
    fetch('http://localhost:8080/delete/' + id, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((res) => {
      setIsLoading(true);
    });
  };

  const saveDatabase = () => {
    if (!email || !password) {
      return;
    }

    const customer = {
      email,
      password,
    };

    if (uuid) {
      customer.uuid = uuid;
    }

    fetch('http://localhost:8080/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(customer),
    })
      .then((res) => {
        setIsLoading(true);
        setEmail('');
        setPassword('');
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    setIsLoading(false);
    fetch('http://localhost:8080')
      .then((res) => res.json())
      .then((data) => {
        setCustomers(data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [isLoading]);

  return (
    <div className="content">
      <h1>Quản Lý Danh Bạ</h1>
      <div className="input">
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="text"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button onClick={addContact}>{isEdit ? 'Cập Nhật' : 'Thêm Mới'}</button>
      </div>
      <table className="list">
        <thead>
          <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((contact, index) => (
            <tr key={index}>
              <td>{contact.email}</td>
              <td>{contact.password}</td>
              <td>
                <button onClick={() => editContact(contact.uuid)}>Sửa</button>
                <button onClick={() => deleteContact(contact.uuid)}>Xóa</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
