

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

export {
    saveDatabase,
}