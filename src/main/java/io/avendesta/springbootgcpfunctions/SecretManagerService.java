package io.avendesta.springbootgcpfunctions;

import com.google.cloud.secretmanager.v1.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SecretManagerService {

    private final String projectId = "barbie-twix";
    private final String secretName = "sample-secret";

    public String getSecret() {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            String secretVersionName = String.format("projects/%s/secrets/%s/versions/1", projectId, secretName);
            SecretPayload payload = client.accessSecretVersion(secretVersionName).getPayload();
            return payload.getData().toStringUtf8();
        } catch (IOException e) {
            throw new RuntimeException("Error fetching secret from Secret Manager", e);
        }
    }

    public String getAllSecrets() {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            String parent = "projects/" + projectId;

            ListSecretsRequest request = ListSecretsRequest.newBuilder()
                    .setParent(parent)
                    .build();

            client.listSecrets(request).iterateAll()
                    .forEach(secret -> {
                        SecretName secretName = SecretName.parse(secret.getName());
                        System.out.println("Secret Name: " + secretName.getSecret());
                    });
        } catch (IOException e) {
            throw new RuntimeException("Error fetching secret from Secret Manager", e);
        }
        return null;
    }
}
