# 🌈 kubernetes-k8s-commonds-

## 🚀 Complete Kubernetes Developer Workflow

> **Source Code → Docker Image → Minikube → Deployment → Service → Runnable Application**

Complete command flow from source code → Docker image → Minikube → Deployment → Service → runnable application, here is a clean CMD-only sequence you can reuse.

---

## ⚙️ Configuration

Assume:

```text
Application name: kubernetes-k8s
Image version:    1.0
Application port: 8080
Deployment name:  kubernetes-k8s
Service name:     kubernetes-k8s-service
```

---

# 📁 1. Move to Your Project Folder

First move to your project folder:

```cmd
cd "C:\Users\ADMIN\Java workSpace\Kubernetes-K8S"
```

---

# 🐳 2. Verify Docker

Verify Docker is running:

```cmd
docker version
```

---

# 🏗️ 3. Build Docker Image

Build your application Docker image:

```cmd
docker build -t kubernetes-k8s:1.0 .
```

Verify the image:

```cmd
docker images
```

You should see:

```text
kubernetes-k8s   1.0
```

---

# ☸️ 4. Verify Minikube

Now verify Minikube is running:

```cmd
minikube status
```

If it is not running:

```cmd
minikube start --driver=docker
```

---

# 🖥️ 5. Verify Kubernetes Node

Verify Kubernetes node:

```cmd
kubectl get nodes
```

---

# 📦 6. Load Docker Image into Minikube

Now load your local Docker image into Minikube:

```cmd
minikube image load kubernetes-k8s:1.0
```

Verify Minikube has the image:

```cmd
minikube image ls
```

---

# 🚀 7. Create the Deployment

Now create the Deployment:

```cmd
kubectl create deployment kubernetes-k8s --image=kubernetes-k8s:1.0
```

Set the local image pull policy so Kubernetes does not try Docker Hub:

```cmd
kubectl patch deployment kubernetes-k8s -p "{\"spec\":{\"template\":{\"spec\":{\"containers\":[{\"name\":\"kubernetes-k8s\",\"image\":\"kubernetes-k8s:1.0\",\"imagePullPolicy\":\"Never\"}]}}}}"
```

---

# 🔍 8. Check Deployment and Pods

Check Deployment status:

```cmd
kubectl get deployments
```

Check Pods:

```cmd
kubectl get pods
```

For more details:

```cmd
kubectl get pods -o wide
```

If the Pod is not running:

```cmd
kubectl describe pod <pod-name>
```

Check logs:

```cmd
kubectl logs <pod-name>
```

For live logs:

```cmd
kubectl logs -f <pod-name>
```

---

# 🌐 9. Expose the Application

Now expose the Deployment through a Service:

```cmd
kubectl expose deployment kubernetes-k8s --type=NodePort --port=8080 --target-port=8080 --name=kubernetes-k8s-service
```

Check the Service:

```cmd
kubectl get services
```

---

# 🔗 10. Get the Application URL

Get the application URL:

```cmd
minikube service kubernetes-k8s-service --url
```

You may get something like:

```text
http://127.0.0.1:49841
```

Then open:

```text
http://127.0.0.1:49841/message
```

or whatever endpoint your Spring Boot application exposes.

---

# ✅ Full First-Time Flow

Your full first-time flow is therefore:

```cmd
cd "C:\Users\ADMIN\Java workSpace\Kubernetes-K8S"

docker build -t kubernetes-k8s:1.0 .

docker images

minikube status

minikube start --driver=docker

kubectl get nodes

minikube image load kubernetes-k8s:1.0

minikube image ls

kubectl create deployment kubernetes-k8s --image=kubernetes-k8s:1.0

kubectl patch deployment kubernetes-k8s -p "{\"spec\":{\"template\":{\"spec\":{\"containers\":[{\"name\":\"kubernetes-k8s\",\"image\":\"kubernetes-k8s:1.0\",\"imagePullPolicy\":\"Never\"}]}}}}"

kubectl get deployments

kubectl get pods

kubectl expose deployment kubernetes-k8s --type=NodePort --port=8080 --target-port=8080 --name=kubernetes-k8s-service

kubectl get services

minikube service kubernetes-k8s-service --url
```

---

# 🔄 New Code Version Workflow

After that, for a new code version, you do not recreate the Deployment and Service.

Suppose you change the code and want version 2.0.

### 🏗️ Build the new image:

```cmd
docker build -t kubernetes-k8s:2.0 .
```

### 📦 Load it into Minikube:

```cmd
minikube image load kubernetes-k8s:2.0
```

### 🔄 Update the existing Deployment:

```cmd
kubectl set image deployment/kubernetes-k8s kubernetes-k8s=kubernetes-k8s:2.0
```

### 👀 Watch rollout status:

```cmd
kubectl rollout status deployment/kubernetes-k8s
```

### 🔍 Check Pods:

```cmd
kubectl get pods
```

### 🖼️ Check the image currently configured:

```cmd
kubectl describe deployment kubernetes-k8s
```

---

# ↩️ Rollback

For rollback:

```cmd
kubectl rollout history deployment/kubernetes-k8s
```

Then:

```cmd
kubectl rollout undo deployment/kubernetes-k8s
```

---

# 📈 Scaling

For scaling:

```cmd
kubectl scale deployment kubernetes-k8s --replicas=3
```

Check:

```cmd
kubectl get pods
```

---

# 📊 CPU and Memory

For CPU and memory:

```cmd
kubectl top pods
```

If metrics are not available:

```cmd
minikube addons enable metrics-server
```

---

# ❤️ Pod Restart and Status

For restart and status:

```cmd
kubectl get pods -o custom-columns="POD:.metadata.name,STATUS:.status.phase,RESTARTS:.status.containerStatuses[*].restartCount"
```

---

# ⚠️ OOMKilled Checks

For OOMKilled checks:

```cmd
kubectl get pods -o custom-columns="POD:.metadata.name,LAST_REASON:.status.containerStatuses[*].lastState.terminated.reason"
```

---

# 🧾 Previous Crash Logs

For previous crash logs:

```cmd
kubectl logs <pod-name> --previous
```

---

# 📊 Kubernetes Dashboard

For the Dashboard:

```cmd
minikube dashboard
```

---

# 🛑 Stop Minikube

And when you finish for the day:

```cmd
minikube stop
```

Start again later:

```cmd
minikube start
```

---

# 🌟 Full Developer Lifecycle

The full developer lifecycle is:

```text
💻 Code
   ↓
🐳 docker build
   ↓
📦 Docker Image
   ↓
☸️ minikube image load
   ↓
🚀 Deployment
   ↓
🔁 ReplicaSet
   ↓
🟢 Pod
   ↓
📦 Container
   ↓
🌐 Service
   ↓
🌍 Browser / Client
```

---

# 🔄 Every Later Code Change

And for every later code change:

```text
💻 Code change
   ↓
📦 New image version
   ↓
☸️ Load image
   ↓
🚀 Update Deployment
   ↓
🔄 Rolling update
   ↓
🟢 New Pods
```

---

## 🎯 Final Workflow

> **Code → Docker → Minikube → Kubernetes Deployment → Pods → Service → Application**

That is the clean end-to-end Kubernetes developer workflow you should practice.
